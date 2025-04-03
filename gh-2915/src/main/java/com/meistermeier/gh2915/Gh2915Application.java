package com.meistermeier.gh2915;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableNeo4jRepositories(considerNestedRepositories = true)
public class Gh2915Application {

	public static void main(String[] args) {
		SpringApplication.run(Gh2915Application.class, args);
	}

	@Bean
	public Configuration cypherDslConfiguration() {
		return Configuration.newConfig().withDialect(Dialect.NEO4J_5).build();
	}

	@Bean
	public CommandLineRunner asdf(@Autowired OrderRepository repository, @Autowired SomethingRepository repository2) {
		return args -> {
			repository2.deleteAll();

			SomethingWithCompositeProperty entity = new SomethingWithCompositeProperty();
			entity.setName("test1");
			entity.setProperties(Map.of("hallo", List.of("spencer")));
			repository2.save(entity);

			var theEntity = repository2.findAll().get(0);
var objectMapper = new ObjectMapper();
SimpleModule module = new SimpleModule();
module.addSerializer(Map.class, new JsonSerializer<Object>() {
	@Override
	public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(o.toString());
	}
});
objectMapper.registerModule(module);
var json = objectMapper.writerFor(SomethingWithCompositeProperty.class).writeValueAsString(theEntity);

			System.out.println(json);
//			System.out.println(theEntity.getProperties());
		};
//		return args -> {
//			var watch = new StopWatch();
//			watch.start();
//			Order theOrder = repository.getTheOrder();
//			System.out.println(theOrder);
//			watch.stop();
//			System.out.println(theOrder.summary());
//			System.out.println(watch.getTotalTimeMillis());
//		};
	}


	@Node
	public record Order(@Id @GeneratedValue String id, @Relationship("TUPLES") List<Tuple> tuples) {
		public int summary() {
			return tuples.size() + tuples.stream().map(tuple -> tuple.items.size() + tuple.items.stream().map(item -> item.subItems.size()).reduce(0, Integer::sum)).reduce(0, Integer::sum);
		}
	}

	@Node
	public record Tuple(@Id @GeneratedValue String id, String name, @Relationship("TUPLES") List<Tuple> tuples, @Relationship("ITEM") List<Item> items) {
	}

	@Node
	public record Item(@Id @GeneratedValue String id, String name, @Relationship("SUBITEM") List<SubItem> subItems) {
	}

	@Node
	public record SubItem(@Id @GeneratedValue String id, String name) {
	}

	@Node("Something")
	public static class SomethingWithCompositeProperty {
		@Id
		@GeneratedValue
		String id;

		String name;

		@CompositeProperty
		Map<String, Collection<Object>> properties;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Map<String, Collection<Object>> getProperties() {
			return properties;
		}

		public void setProperties(Map<String, Collection<Object>> properties) {
			this.properties = properties;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

	public interface OrderRepository extends Neo4jRepository<Order, String> {
		@Query("MATCH p1=(o:Order)-[tttt:TUPLES]->(asdf:Tuple)-[rt:TUPLES]->(nt:Tuple)-[ri:ITEM]->(ni:Item) RETURN o, collect(nodes(p1)), collect(relationships(p1))")
		Order getTheOrder();
	}

	public interface SomethingRepository extends Neo4jRepository<SomethingWithCompositeProperty, String> {}
}
