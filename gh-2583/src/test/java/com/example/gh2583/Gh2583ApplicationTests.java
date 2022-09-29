package com.example.gh2583;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class Gh2583ApplicationTests {

	@Autowired
	private Driver driver;

	@Autowired
	private TxjmNodeEntityRepository repository;

	@BeforeEach
	void setup() {
		try (var session = driver.session()) {
			session.run("MATCH (n) detach delete n").consume();
//			generateUserData(session);
			generateSyntethicData(session);
		}
	}

	private ResultSummary generateSyntethicData(Session session) {
		return session.run("""
				CREATE (n:hktxjm)
				-[:guanXi]->(m:hktxjm)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				-[:guanXi]->(n)
				-[:guanXi]->(m)
				"""
		).consume();
	}

	private ResultSummary generateUserData(Session session) {
		return session.run("""
				CREATE (testliYiXGF1:hktxjm {uid:"11c73f0c-2289-11ed-915b-6c4b907924b0",name:"testliYiXGF1",shape:"hktxjm.zhanLue",subShape:"liYiXGF",subShapeName:"liYiXGF",`props.description`:"mydescription"})
				CREATE (testquDongL1:hktxjm {uid:"11c73f0d-2289-11ed-b185-6c4b907924b0",name:"testquDongL1",shape:"hktxjm.zhanLue",subShape:"quDongL",subShapeName:"quDongL",`props.description`:"mydescription"})
				CREATE (testmuBiao1:hktxjm {uid:"11c73f0e-2289-11ed-b0b6-6c4b907924b0",name:"testmuBiao1",shape:"hktxjm.zhanLue",subShape:"muBiao",subShapeName:"muBiao",`props.description`:"mydescription"})
				CREATE (testyuanZe1:hktxjm {uid:"11c73f0f-2289-11ed-ac78-6c4b907924b0",name:"testyuanZe1",shape:"hktxjm.zhanLue",subShape:"yuanZe",subShapeName:"yuanZe",`props.description`:"mydescription"})
				CREATE (testnengLi1:hktxjm {uid:"11c76609-2289-11ed-a108-6c4b907924b0",name:"testnengLi1",shape:"hktxjm.zhanLue",subShape:"nengLi",subShapeName:"nengLi",`props.description`:"mydescription"})
				CREATE (testceDu1:hktxjm {uid:"11c7660a-2289-11ed-804f-6c4b907924b0",name:"testceDu1",shape:"hktxjm.zhanLue",subShape:"ceDu",subShapeName:"ceDu",`props.description`:"mydescription"})
				CREATE (testfengXian1:hktxjm {uid:"11c7660b-2289-11ed-acc5-6c4b907924b0",name:"testfengXian1",shape:"hktxjm.zhanLue",subShape:"fengXian",subShapeName:"fengXian",`props.description`:"mydescription"})
				CREATE (testziYuan1:hktxjm {uid:"11c7660c-2289-11ed-b7c7-6c4b907924b0",name:"testziYuan1",shape:"hktxjm.zhanLue",subShape:"ziYuan",subShapeName:"ziYuan",`props.description`:"mydescription"})
				CREATE (testyuanJing1:hktxjm {uid:"11c7660d-2289-11ed-8e3d-6c4b907924b0",name:"testyuanJing1",shape:"hktxjm.zhanLue",subShape:"yuanJing",subShapeName:"yuanJing",`props.description`:"mydescription"})
				CREATE (testjiaZhiL1:hktxjm {uid:"11c7660e-2289-11ed-b069-6c4b907924b0",name:"testjiaZhiL1",shape:"hktxjm.yeWu",subShape:"jiaZhiL",subShapeName:"jiaZhiL",`props.description`:"mydescription"})
				CREATE (testzuJian1:hktxjm {uid:"11c7660f-2289-11ed-859b-6c4b907924b0",name:"testzuJian1",shape:"hktxjm.yeWu",subShape:"zuJian",subShapeName:"zuJian",`props.description`:"mydescription"})
				CREATE (testshiJian1:hktxjm {uid:"11c76610-2289-11ed-ac93-6c4b907924b0",name:"testshiJian1",shape:"hktxjm.yeWu",subShape:"shiJian",subShapeName:"shiJian",`props.description`:"mydescription"})
				CREATE (testduiXiang1:hktxjm {uid:"11c76611-2289-11ed-a962-6c4b907924b0",name:"testduiXiang1",shape:"hktxjm.yeWu",subShape:"duiXiang",subShapeName:"duiXiang",`props.description`:"mydescription"})
				CREATE (testguiZe1:hktxjm {uid:"11c76612-2289-11ed-bfc8-6c4b907924b0",name:"testguiZe1",shape:"hktxjm.yeWu",subShape:"guiZe",subShapeName:"guiZe",`props.description`:"mydescription"})
				CREATE (testzuZhiDY1:hktxjm {uid:"11c76613-2289-11ed-9cc1-6c4b907924b0",name:"testzuZhiDY1",shape:"hktxjm.yeWu",subShape:"zuZhiDY",subShapeName:"zuZhiDY",`props.description`:"mydescription"})
				CREATE (testjueSe1:hktxjm {uid:"11c76614-2289-11ed-8ff1-6c4b907924b0",name:"testjueSe1",shape:"hktxjm.yeWu",subShape:"jueSe",subShapeName:"jueSe",`props.description`:"mydescription"})
				CREATE (testgangWei1:hktxjm {uid:"11c78d15-2289-11ed-96f1-6c4b907924b0",name:"testgangWei1",shape:"hktxjm.yeWu",subShape:"gangWei",subShapeName:"gangWei",`props.description`:"mydescription"})
				CREATE (testliuCheng1:hktxjm {uid:"11c78d16-2289-11ed-9a76-6c4b907924b0",name:"testliuCheng1",shape:"hktxjm.yeWu",subShape:"liuCheng",subShapeName:"liuCheng",`props.description`:"mydescription"})
				CREATE (testzhiZe1:hktxjm {uid:"11c78d17-2289-11ed-afd6-6c4b907924b0",name:"testzhiZe1",shape:"hktxjm.yeWu",subShape:"zhiZe",subShapeName:"zhiZe",`props.description`:"mydescription"})
				CREATE (testkaiShiSJ1:hktxjm {uid:"11c78d18-2289-11ed-9a7b-6c4b907924b0",name:"testkaiShiSJ1",shape:"hktxjm.liuCheng",subShape:"kaiShiSJ",subShapeName:"kaiShiSJ",`props.description`:"mydescription"})
				CREATE (testjieShuSJ1:hktxjm {uid:"11c78d19-2289-11ed-80dc-6c4b907924b0",name:"testjieShuSJ1",shape:"hktxjm.liuCheng",subShape:"jieShuSJ",subShapeName:"jieShuSJ",`props.description`:"mydescription"})
				CREATE (testyongHuRW1:hktxjm {uid:"11c7db26-2289-11ed-a662-6c4b907924b0",name:"testyongHuRW1",shape:"hktxjm.liuCheng",subShape:"yongHuRW",subShapeName:"yongHuRW",`props.description`:"mydescription"})
				CREATE (testshouGongRW1:hktxjm {uid:"11c7db27-2289-11ed-b877-6c4b907924b0",name:"testshouGongRW1",shape:"hktxjm.liuCheng",subShape:"shouGongRW",subShapeName:"shouGongRW",`props.description`:"mydescription"})
				CREATE (testfuWuRW1:hktxjm {uid:"11c7db28-2289-11ed-b135-6c4b907924b0",name:"testfuWuRW1",shape:"hktxjm.liuCheng",subShape:"fuWuRW",subShapeName:"fuWuRW",`props.description`:"mydescription"})
				CREATE (testxunHuanRW1:hktxjm {uid:"11c7db29-2289-11ed-a37a-6c4b907924b0",name:"testxunHuanRW1",shape:"hktxjm.liuCheng",subShape:"xunHuanRW",subShapeName:"xunHuanRW",`props.description`:"mydescription"})
				CREATE (testziLiuC1:hktxjm {uid:"11c7db2a-2289-11ed-a7b9-6c4b907924b0",name:"testziLiuC1",shape:"hktxjm.liuCheng",subShape:"ziLiuC",subShapeName:"ziLiuC",`props.description`:"mydescription"})
				CREATE (testhuoDong1:hktxjm {uid:"11c7db2b-2289-11ed-b8af-6c4b907924b0",name:"testhuoDong1",shape:"hktxjm.liuCheng",subShape:"huoDong",subShapeName:"huoDong",`props.description`:"mydescription"})
				CREATE (testpaiTaWG1:hktxjm {uid:"11c7db2c-2289-11ed-ae5c-6c4b907924b0",name:"testpaiTaWG1",shape:"hktxjm.liuCheng",subShape:"paiTaWG",subShapeName:"paiTaWG",`props.description`:"mydescription"})
				CREATE (testpingXingWG1:hktxjm {uid:"11c7db2d-2289-11ed-80c4-6c4b907924b0",name:"testpingXingWG1",shape:"hktxjm.liuCheng",subShape:"pingXingWG",subShapeName:"pingXingWG",`props.description`:"mydescription"})
				CREATE (testbaoHanWG1:hktxjm {uid:"11c7db2e-2289-11ed-b44e-6c4b907924b0",name:"testbaoHanWG1",shape:"hktxjm.liuCheng",subShape:"baoHanWG",subShapeName:"baoHanWG",`props.description`:"mydescription"})
				CREATE (testshuJuSR1:hktxjm {uid:"11c7db2f-2289-11ed-ab4f-6c4b907924b0",name:"testshuJuSR1",shape:"hktxjm.liuCheng",subShape:"shuJuSR",subShapeName:"shuJuSR",`props.description`:"mydescription"})
				CREATE (testshuJuSC1:hktxjm {uid:"11c8022d-2289-11ed-9036-6c4b907924b0",name:"testshuJuSC1",shape:"hktxjm.liuCheng",subShape:"shuJuSC",subShapeName:"shuJuSC",`props.description`:"mydescription"})
				CREATE (testyingYongY1:hktxjm {uid:"11c8022e-2289-11ed-a045-6c4b907924b0",name:"testyingYongY1",shape:"hktxjm.yingYong",subShape:"yingYongY",subShapeName:"yingYongY",`props.description`:"mydescription"})
				CREATE (testyingYong1:hktxjm {uid:"11c8022f-2289-11ed-8be6-6c4b907924b0",name:"testyingYong1",shape:"hktxjm.yingYong",subShape:"yingYong",subShapeName:"yingYong",`props.description`:"mydescription"})
				CREATE (testyingYongZJ1:hktxjm {uid:"11c80230-2289-11ed-96ea-6c4b907924b0",name:"testyingYongZJ1",shape:"hktxjm.yingYong",subShape:"yingYongZJ",subShapeName:"yingYongZJ",`props.description`:"mydescription"})
				CREATE (testyingYongFW1:hktxjm {uid:"11c80231-2289-11ed-b04e-6c4b907924b0",name:"testyingYongFW1",shape:"hktxjm.yingYong",subShape:"yingYongFW",subShapeName:"yingYongFW",`props.description`:"mydescription"})
				CREATE (testyingYongJK1:hktxjm {uid:"11c80232-2289-11ed-834c-6c4b907924b0",name:"testyingYongJK1",shape:"hktxjm.yingYong",subShape:"yingYongJK",subShapeName:"yingYongJK",`props.description`:"mydescription"})
				CREATE (testjieDian1:hktxjm {uid:"11c80233-2289-11ed-898e-6c4b907924b0",name:"testjieDian1",shape:"hktxjm.jiShu",subShape:"jieDian",subShapeName:"jieDian",`props.description`:"mydescription"})
				CREATE (testsheBei1:hktxjm {uid:"11c8295d-2289-11ed-b54e-6c4b907924b0",name:"testsheBei1",shape:"hktxjm.jiShu",subShape:"sheBei",subShapeName:"sheBei",`props.description`:"mydescription"})
				CREATE (testxiTongRJ1:hktxjm {uid:"11c8295e-2289-11ed-91ae-6c4b907924b0",name:"testxiTongRJ1",shape:"hktxjm.jiShu",subShape:"xiTongRJ",subShapeName:"xiTongRJ",`props.description`:"mydescription"})
				CREATE (testjiShuJK1:hktxjm {uid:"11c8295f-2289-11ed-afba-6c4b907924b0",name:"testjiShuJK1",shape:"hktxjm.jiShu",subShape:"jiShuJK",subShapeName:"jiShuJK",`props.description`:"mydescription"})
				CREATE (testjiShuFW1:hktxjm {uid:"11c82960-2289-11ed-892c-6c4b907924b0",name:"testjiShuFW1",shape:"hktxjm.jiShu",subShape:"jiShuFW",subShapeName:"jiShuFW",`props.description`:"mydescription"})
				CREATE (testluJing1:hktxjm {uid:"11c82961-2289-11ed-a6bd-6c4b907924b0",name:"testluJing1",shape:"hktxjm.jiShu",subShape:"luJing",subShapeName:"luJing",`props.description`:"mydescription"})
				CREATE (testtongXinWL1:hktxjm {uid:"11c82962-2289-11ed-b13c-6c4b907924b0",name:"testtongXinWL1",shape:"hktxjm.jiShu",subShape:"tongXinWL",subShapeName:"tongXinWL",`props.description`:"mydescription"})
				CREATE (testzhuangBei1:hktxjm {uid:"11c82963-2289-11ed-8a7e-6c4b907924b0",name:"testzhuangBei1",shape:"hktxjm.wuLi",subShape:"zhuangBei",subShapeName:"zhuangBei",`props.description`:"mydescription"})
				CREATE (testwuLiao1:hktxjm {uid:"11c82964-2289-11ed-b092-6c4b907924b0",name:"testwuLiao1",shape:"hktxjm.wuLi",subShape:"wuLiao",subShapeName:"wuLiao",`props.description`:"mydescription"})
				CREATE (testfenFaWL1:hktxjm {uid:"11c82965-2289-11ed-84ac-6c4b907924b0",name:"testfenFaWL1",shape:"hktxjm.wuLi",subShape:"fenFaWL",subShapeName:"fenFaWL",`props.description`:"mydescription"})
				CREATE (testsheShi1:hktxjm {uid:"11c85055-2289-11ed-a7eb-6c4b907924b0",name:"testsheShi1",shape:"hktxjm.wuLi",subShape:"sheShi",subShapeName:"sheShi",`props.description`:"mydescription"})
				CREATE (testshuJuST1:hktxjm {uid:"11c85056-2289-11ed-947b-6c4b907924b0",name:"testshuJuST1",shape:"hktxjm.shuJu",subShape:"shuJuST",subShapeName:"shuJuST",`props.description`:"mydescription"})
				CREATE (testshuJuYS1:hktxjm {uid:"11c85057-2289-11ed-96dd-6c4b907924b0",name:"testshuJuYS1",shape:"hktxjm.shuJu",subShape:"shuJuYS",subShapeName:"shuJuYS",`props.description`:"mydescription"})
				CREATE (testzhiDu1:hktxjm {uid:"11c85058-2289-11ed-85eb-6c4b907924b0",name:"testzhiDu1",shape:"hktxjm.biaoZhunGF",subShape:"zhiDu",subShapeName:"zhiDu",`props.description`:"mydescription"})
				CREATE (testheGuiTK1:hktxjm {uid:"11c85059-2289-11ed-a4b4-6c4b907924b0",name:"testheGuiTK1",shape:"hktxjm.biaoZhunGF",subShape:"heGuiTK",subShapeName:"heGuiTK",`props.description`:"mydescription"})
				CREATE (testheGuiWJ1:hktxjm {uid:"11c8505a-2289-11ed-b011-6c4b907924b0",name:"testheGuiWJ1",shape:"hktxjm.biaoZhunGF",subShape:"heGuiWJ",subShapeName:"heGuiWJ",`props.description`:"mydescription"})
				CREATE (testheGuiCJYQ1:hktxjm {uid:"11c87784-2289-11ed-b9d2-6c4b907924b0",name:"testheGuiCJYQ1",shape:"hktxjm.biaoZhunGF",subShape:"heGuiCJYQ",subShapeName:"heGuiCJYQ",`props.description`:"mydescription"})
				CREATE (testfengXianLB1:hktxjm {uid:"11c87785-2289-11ed-859b-6c4b907924b0",name:"testfengXianLB1",shape:"hktxjm.biaoZhunGF",subShape:"fengXianLB",subShapeName:"fengXianLB",`props.description`:"mydescription"})
				CREATE (testxuQiu1:hktxjm {uid:"11c87786-2289-11ed-8754-6c4b907924b0",name:"testxuQiu1",shape:"hktxjm.chiXuGJ",subShape:"xuQiu",subShapeName:"xuQiu",`props.description`:"mydescription"})
				CREATE (testchaJu1:hktxjm {uid:"11c87787-2289-11ed-b9a5-6c4b907924b0",name:"testchaJu1",shape:"hktxjm.chiXuGJ",subShape:"chaJu",subShapeName:"chaJu",`props.description`:"mydescription"})
				CREATE (testjieDuan1:hktxjm {uid:"11c87788-2289-11ed-b465-6c4b907924b0",name:"testjieDuan1",shape:"hktxjm.chiXuGJ",subShape:"jieDuan",subShapeName:"jieDuan",`props.description`:"mydescription"})
				CREATE (testyueShu1:hktxjm {uid:"11c87789-2289-11ed-859d-6c4b907924b0",name:"testyueShu1",shape:"hktxjm.chiXuGJ",subShape:"yueShu",subShapeName:"yueShu",`props.description`:"mydescription"})
				CREATE (testjiaoFuW1:hktxjm {uid:"11c8778a-2289-11ed-8eca-6c4b907924b0",name:"testjiaoFuW1",shape:"hktxjm.chiXuGJ",subShape:"jiaoFuW",subShapeName:"jiaoFuW",`props.description`:"mydescription"})
				CREATE (testgongZuoB1:hktxjm {uid:"11c89e68-2289-11ed-8d46-6c4b907924b0",name:"testgongZuoB1",shape:"hktxjm.chiXuGJ",subShape:"gongZuoB",subShapeName:"gongZuoB",`props.description`:"mydescription"})
				CREATE (testshiShiSJ1:hktxjm {uid:"11c89e69-2289-11ed-ab97-6c4b907924b0",name:"testshiShiSJ1",shape:"hktxjm.chiXuGJ",subShape:"shiShiSJ",subShapeName:"shiShiSJ",`props.description`:"mydescription"})
				CREATE (testweiZhi1:hktxjm {uid:"11c89e6a-2289-11ed-8906-6c4b907924b0",name:"testweiZhi1",shape:"hktxjm.chiXuGJ",subShape:"weiZhi",subShapeName:"weiZhi",`props.description`:"mydescription"})
				CREATE (testquDongL1)-[:guanXi {uid:"11c8ec90-2289-11ed-9946-6c4b907924b0",name:"fenPei", shape:"hktxjm.guanXi",subShape:"fenPei",subShapeName:"fenPei",`props.description`:"mydescription"}]->(testheGuiCJYQ1)
				CREATE (testpingXingWG1)-[:guanXi {uid:"11c8ec91-2289-11ed-9a6c-6c4b907924b0",name:"fuWu", shape:"hktxjm.guanXi",subShape:"fuWu",subShapeName:"fuWu",`props.description`:"mydescription"}]->(testshuJuSR1)
				CREATE (testguiZe1)-[:guanXi {uid:"11c8ec92-2289-11ed-95cf-6c4b907924b0",name:"shunXuLX", shape:"hktxjm.guanXi",subShape:"shunXuLX",subShapeName:"shunXuLX",`props.description`:"mydescription"}]->(testshuJuSR1)
				CREATE (testduiXiang1)-[:guanXi {uid:"11c8ec93-2289-11ed-8f22-6c4b907924b0",name:"fuWu", shape:"hktxjm.guanXi",subShape:"fuWu",subShapeName:"fuWu",`props.description`:"mydescription"}]->(testxiTongRJ1)
				CREATE (testxiTongRJ1)-[:guanXi {uid:"11c91395-2289-11ed-bc13-6c4b907924b0",name:"juHe", shape:"hktxjm.guanXi",subShape:"juHe",subShapeName:"juHe",`props.description`:"mydescription"}]->(testzhiZe1)
				CREATE (testyongHuRW1)-[:guanXi {uid:"11c91396-2289-11ed-8e3b-6c4b907924b0",name:"fanHua", shape:"hktxjm.guanXi",subShape:"fanHua",subShapeName:"fanHua",`props.description`:"mydescription"}]->(testduiXiang1)
				CREATE (testgangWei1)-[:guanXi {uid:"11c91397-2289-11ed-9afb-6c4b907924b0",name:"juHe", shape:"hktxjm.guanXi",subShape:"juHe",subShapeName:"juHe",`props.description`:"mydescription"}]->(testjiaoFuW1)
				CREATE (testfenFaWL1)-[:guanXi {uid:"11c91398-2289-11ed-80a6-6c4b907924b0",name:"fenPei", shape:"hktxjm.guanXi",subShape:"fenPei",subShapeName:"fenPei",`props.description`:"mydescription"}]->(testheGuiTK1)
				CREATE (testheGuiTK1)-[:guanXi {uid:"11c93aa7-2289-11ed-9fe9-6c4b907924b0",name:"fangWen", shape:"hktxjm.guanXi",subShape:"fangWen",subShapeName:"fangWen",`props.description`:"mydescription"}]->(testjiaZhiL1)
				CREATE (testjueSe1)-[:guanXi {uid:"11c93aa8-2289-11ed-94b0-6c4b907924b0",name:"liuXiang", shape:"hktxjm.guanXi",subShape:"liuXiang",subShapeName:"liuXiang",`props.description`:"mydescription"}]->(testzhiZe1)
				CREATE (testyuanZe1)-[:guanXi {uid:"11c988c2-2289-11ed-9ae6-6c4b907924b0",name:"fangWen", shape:"hktxjm.guanXi",subShape:"fangWen",subShapeName:"fangWen",`props.description`:"mydescription"}]->(testnengLi1)
				CREATE (testquDongL1)-[:guanXi {uid:"11c9fe04-2289-11ed-aace-6c4b907924b0",name:"fangWen", shape:"hktxjm.guanXi",subShape:"fangWen",subShapeName:"fangWen",`props.description`:"mydescription"}]->(testshuJuST1)
				CREATE (testpingXingWG1)-[:guanXi {uid:"11c9fe05-2289-11ed-8cfc-6c4b907924b0",name:"shuJuGL", shape:"hktxjm.guanXi",subShape:"shuJuGL",subShapeName:"shuJuGL",`props.description`:"mydescription"}]->(testquDongL1)
				CREATE (testduiXiang1)-[:guanXi {uid:"11ca2515-2289-11ed-9e5c-6c4b907924b0",name:"juHe", shape:"hktxjm.guanXi",subShape:"juHe",subShapeName:"juHe",`props.description`:"mydescription"}]->(testzhuangBei1)
				CREATE (testheGuiTK1)-[:guanXi {uid:"11ca2516-2289-11ed-86af-6c4b907924b0",name:"yingXiang", shape:"hktxjm.guanXi",subShape:"yingXiang",subShapeName:"yingXiang",`props.description`:"mydescription"}]->(testgangWei1)
				CREATE (testjueSe1)-[:guanXi {uid:"11ca2517-2289-11ed-b565-6c4b907924b0",name:"liuXiang", shape:"hktxjm.guanXi",subShape:"liuXiang",subShapeName:"liuXiang",`props.description`:"mydescription"}]->(testduiXiang1)
				CREATE (testshiShiSJ1)-[:guanXi {uid:"11ca2518-2289-11ed-8e10-6c4b907924b0",name:"liuXiang", shape:"hktxjm.guanXi",subShape:"liuXiang",subShapeName:"liuXiang",`props.description`:"mydescription"}]->(testfuWuRW1)
				CREATE (testheGuiTK1)-[:guanXi {uid:"11ca4c40-2289-11ed-aa8e-6c4b907924b0",name:"yingXiang", shape:"hktxjm.guanXi",subShape:"yingXiang",subShapeName:"yingXiang",`props.description`:"mydescription"}]->(testtongXinWL1)
				CREATE (testziYuan1)-[:guanXi {uid:"11ca732c-2289-11ed-a132-6c4b907924b0",name:"fuWu", shape:"hktxjm.guanXi",subShape:"fuWu",subShapeName:"fuWu",`props.description`:"mydescription"}]->(testzhiDu1)
				CREATE (testheGuiCJYQ1)-[:guanXi {uid:"11ca732d-2289-11ed-98f8-6c4b907924b0",name:"fanHua", shape:"hktxjm.guanXi",subShape:"fanHua",subShapeName:"fanHua",`props.description`:"mydescription"}]->(testzuJian1)
				CREATE (testpaiTaWG1)-[:guanXi {uid:"11ca732e-2289-11ed-a5e6-6c4b907924b0",name:"zuHe", shape:"hktxjm.guanXi",subShape:"zuHe",subShapeName:"zuHe",`props.description`:"mydescription"}]->(testnengLi1)
				CREATE (testhuoDong1)-[:guanXi {uid:"11ca732f-2289-11ed-9e30-6c4b907924b0",name:"yingXiang", shape:"hktxjm.guanXi",subShape:"yingXiang",subShapeName:"yingXiang",`props.description`:"mydescription"}]->(testyueShu1)
				CREATE (testziLiuC1)-[:guanXi {uid:"11ca9a2e-2289-11ed-a045-6c4b907924b0",name:"shunXuLX", shape:"hktxjm.guanXi",subShape:"shunXuLX",subShapeName:"shunXuLX",`props.description`:"mydescription"}]->(testluJing1)
				CREATE (testtongXinWL1)-[:guanXi {uid:"11ca9a2f-2289-11ed-9671-6c4b907924b0",name:"juHe", shape:"hktxjm.guanXi",subShape:"juHe",subShapeName:"juHe",`props.description`:"mydescription"}]->(testtongXinWL1)
				CREATE (testshuJuSR1)-[:guanXi {uid:"11ca9a30-2289-11ed-9939-6c4b907924b0",name:"liuXiang", shape:"hktxjm.guanXi",subShape:"liuXiang",subShapeName:"liuXiang",`props.description`:"mydescription"}]->(testceDu1)
				CREATE (testnengLi1)-[:guanXi {uid:"11cac142-2289-11ed-a9de-6c4b907924b0",name:"shiXian", shape:"hktxjm.guanXi",subShape:"shiXian",subShapeName:"shiXian",`props.description`:"mydescription"}]->(testnengLi1)
				CREATE (testnengLi1)-[:guanXi {uid:"11cac143-2289-11ed-af4d-6c4b907924b0",name:"chuFa", shape:"hktxjm.guanXi",subShape:"chuFa",subShapeName:"chuFa",`props.description`:"mydescription"}]->(testjueSe1)
				CREATE (testheGuiTK1)-[:guanXi {uid:"11cac144-2289-11ed-9968-6c4b907924b0",name:"shunXuLX", shape:"hktxjm.guanXi",subShape:"shunXuLX",subShapeName:"shunXuLX",`props.description`:"mydescription"}]->(testjiShuFW1)
				CREATE (testyingYongY1)-[:guanXi {uid:"11cac145-2289-11ed-ba52-6c4b907924b0",name:"fuWu", shape:"hktxjm.guanXi",subShape:"fuWu",subShapeName:"fuWu",`props.description`:"mydescription"}]->(testceDu1)
				CREATE (testfenFaWL1)-[:guanXi {uid:"11cac146-2289-11ed-92ea-6c4b907924b0",name:"liuXiang", shape:"hktxjm.guanXi",subShape:"liuXiang",subShapeName:"liuXiang",`props.description`:"mydescription"}]->(testyongHuRW1)
				CREATE (testzhiDu1)-[:guanXi {uid:"11cae84c-2289-11ed-9fb1-6c4b907924b0",name:"chuFa", shape:"hktxjm.guanXi",subShape:"chuFa",subShapeName:"chuFa",`props.description`:"mydescription"}]->(testsheBei1)
				CREATE (testduiXiang1)-[:guanXi {uid:"11cb0f5d-2289-11ed-8ddd-6c4b907924b0",name:"fenPei", shape:"hktxjm.guanXi",subShape:"fenPei",subShapeName:"fenPei",`props.description`:"mydescription"}]->(testyuanJing1)
				CREATE (testchaJu1)-[:guanXi {uid:"11cb0f5e-2289-11ed-bfbb-6c4b907924b0",name:"guanLian", shape:"hktxjm.guanXi",subShape:"guanLian",subShapeName:"guanLian",`props.description`:"mydescription"}]->(testshuJuSC1)
				CREATE (testceDu1)-[:guanXi {uid:"11cb0f5f-2289-11ed-ae97-6c4b907924b0",name:"chuFaSX", shape:"hktxjm.guanXi",subShape:"chuFaSX",subShapeName:"chuFaSX",`props.description`:"mydescription"}]->(testyueShu1)
				CREATE (testyingYongZJ1)-[:guanXi {uid:"11cb0f60-2289-11ed-913f-6c4b907924b0",name:"fanHua", shape:"hktxjm.guanXi",subShape:"fanHua",subShapeName:"fanHua",`props.description`:"mydescription"}]->(testfuWuRW1)
				CREATE (testshiShiSJ1)-[:guanXi {uid:"11cb3667-2289-11ed-9cba-6c4b907924b0",name:"liuXiang", shape:"hktxjm.guanXi",subShape:"liuXiang",subShapeName:"liuXiang",`props.description`:"mydescription"}]->(testkaiShiSJ1)
				CREATE (testquDongL1)-[:guanXi {uid:"11cb3668-2289-11ed-a6da-6c4b907924b0",name:"shuJuGL", shape:"hktxjm.guanXi",subShape:"shuJuGL",subShapeName:"shuJuGL",`props.description`:"mydescription"}]->(testheGuiWJ1)
				CREATE (testshuJuST1)-[:guanXi {uid:"11cb3669-2289-11ed-a890-6c4b907924b0",name:"zuHe", shape:"hktxjm.guanXi",subShape:"zuHe",subShapeName:"zuHe",`props.description`:"mydescription"}]->(testsheShi1)
				CREATE (testliYiXGF1)-[:guanXi {uid:"11cb5d7a-2289-11ed-b9f3-6c4b907924b0",name:"fangWen", shape:"hktxjm.guanXi",subShape:"fangWen",subShapeName:"fangWen",`props.description`:"mydescription"}]->(testxunHuanRW1)
				CREATE (testjieShuSJ1)-[:guanXi {uid:"11cb5d7b-2289-11ed-998c-6c4b907924b0",name:"chuFa", shape:"hktxjm.guanXi",subShape:"chuFa",subShapeName:"chuFa",`props.description`:"mydescription"}]->(testxunHuanRW1)
				CREATE (testyingYongY1)-[:guanXi {uid:"11cb5d7c-2289-11ed-aa0e-6c4b907924b0",name:"shunXuLX", shape:"hktxjm.guanXi",subShape:"shunXuLX",subShapeName:"shunXuLX",`props.description`:"mydescription"}]->(testxuQiu1)
				CREATE (testjieDuan1)-[:guanXi {uid:"11cb8489-2289-11ed-8d26-6c4b907924b0",name:"fenPei", shape:"hktxjm.guanXi",subShape:"fenPei",subShapeName:"fenPei",`props.description`:"mydescription"}]->(testshuJuST1)
				CREATE (testyingYongZJ1)-[:guanXi {uid:"11cb848a-2289-11ed-bc09-6c4b907924b0",name:"fenPei", shape:"hktxjm.guanXi",subShape:"fenPei",subShapeName:"fenPei",`props.description`:"mydescription"}]->(testyongHuRW1)
				CREATE (testnengLi1)-[:guanXi {uid:"11cb848b-2289-11ed-9ad9-6c4b907924b0",name:"zuHe", shape:"hktxjm.guanXi",subShape:"zuHe",subShapeName:"zuHe",`props.description`:"mydescription"}]->(testyingYongJK1)
				CREATE (testshouGongRW1)-[:guanXi {uid:"11cbab95-2289-11ed-8718-6c4b907924b0",name:"yingXiang", shape:"hktxjm.guanXi",subShape:"yingXiang",subShapeName:"yingXiang",`props.description`:"mydescription"}]->(testduiXiang1)
				CREATE (testxuQiu1)-[:guanXi {uid:"11cbab96-2289-11ed-b1ae-6c4b907924b0",name:"chuFa", shape:"hktxjm.guanXi",subShape:"chuFa",subShapeName:"chuFa",`props.description`:"mydescription"}]->(testshiShiSJ1)
				CREATE (testsheBei1)-[:guanXi {uid:"11cbd2d6-2289-11ed-8f1c-6c4b907924b0",name:"shuJuGL", shape:"hktxjm.guanXi",subShape:"shuJuGL",subShapeName:"shuJuGL",`props.description`:"mydescription"}]->(testliuCheng1)
				CREATE (testzuZhiDY1)-[:guanXi {uid:"11cbd2d7-2289-11ed-ab89-6c4b907924b0",name:"shuJuGL", shape:"hktxjm.guanXi",subShape:"shuJuGL",subShapeName:"shuJuGL",`props.description`:"mydescription"}]->(testyuanZe1)
				CREATE (testshouGongRW1)-[:guanXi {uid:"11cbd2d8-2289-11ed-8315-6c4b907924b0",name:"shuJuGL", shape:"hktxjm.guanXi",subShape:"shuJuGL",subShapeName:"shuJuGL",`props.description`:"mydescription"}]->(testyingYongY1)
				CREATE (testshiJian1)-[:guanXi {uid:"11cbf9d4-2289-11ed-9050-6c4b907924b0",name:"chuFaSX", shape:"hktxjm.guanXi",subShape:"chuFaSX",subShapeName:"chuFaSX",`props.description`:"mydescription"}]->(testshuJuSC1)
				CREATE (testjiShuFW1)-[:guanXi {uid:"11cbf9d5-2289-11ed-9d5c-6c4b907924b0",name:"zuHe", shape:"hktxjm.guanXi",subShape:"zuHe",subShapeName:"zuHe",`props.description`:"mydescription"}]->(testshuJuSC1)
				CREATE (testmuBiao1)-[:guanXi {uid:"11cbf9d6-2289-11ed-aaa1-6c4b907924b0",name:"shuJuGL", shape:"hktxjm.guanXi",subShape:"shuJuGL",subShapeName:"shuJuGL",`props.description`:"mydescription"}]->(testliuCheng1)
				CREATE (testzhiDu1)-[:guanXi {uid:"11cbf9d7-2289-11ed-b34a-6c4b907924b0",name:"chuFaSX", shape:"hktxjm.guanXi",subShape:"chuFaSX",subShapeName:"chuFaSX",`props.description`:"mydescription"}]->(testheGuiWJ1)
				CREATE (testnengLi1)-[:guanXi {uid:"11cc20f0-2289-11ed-a096-6c4b907924b0",name:"chuFaSX", shape:"hktxjm.guanXi",subShape:"chuFaSX",subShapeName:"chuFaSX",`props.description`:"mydescription"}]->(testshuJuYS1)
				CREATE (testyuanJing1)-[:guanXi {uid:"11cc20f1-2289-11ed-a948-6c4b907924b0",name:"zuHe", shape:"hktxjm.guanXi",subShape:"zuHe",subShapeName:"zuHe",`props.description`:"mydescription"}]->(testzhiZe1)
				CREATE (testheGuiCJYQ1)-[:guanXi {uid:"11cc20f2-2289-11ed-8742-6c4b907924b0",name:"shunXuLX", shape:"hktxjm.guanXi",subShape:"shunXuLX",subShapeName:"shunXuLX",`props.description`:"mydescription"}]->(testyingYong1)
				CREATE (testshouGongRW1)-[:guanXi {uid:"11cc47e8-2289-11ed-a3d2-6c4b907924b0",name:"yingXiang", shape:"hktxjm.guanXi",subShape:"yingXiang",subShapeName:"yingXiang",`props.description`:"mydescription"}]->(testchaJu1)
				CREATE (testshiJian1)-[:guanXi {uid:"11cc47e9-2289-11ed-8416-6c4b907924b0",name:"shunXuLX", shape:"hktxjm.guanXi",subShape:"shunXuLX",subShapeName:"shunXuLX",`props.description`:"mydescription"}]->(testliYiXGF1)
				CREATE (testceDu1)-[:guanXi {uid:"11cc47ea-2289-11ed-b790-6c4b907924b0",name:"shunXuLX", shape:"hktxjm.guanXi",subShape:"shunXuLX",subShapeName:"shunXuLX",`props.description`:"mydescription"}]->(testyingYongJK1)
				CREATE (testjiShuJK1)-[:guanXi {uid:"11cc47eb-2289-11ed-b332-6c4b907924b0",name:"shuJuGL", shape:"hktxjm.guanXi",subShape:"shuJuGL",subShapeName:"shuJuGL",`props.description`:"mydescription"}]->(testzhuangBei1)
				CREATE (testluJing1)-[:guanXi {uid:"11cc6f0b-2289-11ed-b2b3-6c4b907924b0",name:"zuHe", shape:"hktxjm.guanXi",subShape:"zuHe",subShapeName:"zuHe",`props.description`:"mydescription"}]->(testziYuan1)
				CREATE (testzuZhiDY1)-[:guanXi {uid:"11cc6f0c-2289-11ed-8f2a-6c4b907924b0",name:"fuWu", shape:"hktxjm.guanXi",subShape:"fuWu",subShapeName:"fuWu",`props.description`:"mydescription"}]->(testzhiDu1)
				CREATE (testbaoHanWG1)-[:guanXi {uid:"11cc6f0d-2289-11ed-9933-6c4b907924b0",name:"guanLian", shape:"hktxjm.guanXi",subShape:"guanLian",subShapeName:"guanLian",`props.description`:"mydescription"}]->(testjiaZhiL1)
									"""
		).consume();
	}

	@Test
	void reproducer() {
		Page<TxjmNodeEntity> allWithOutgoingEdge = repository.findAllWithOutgoingEdge(PageRequest.of(0, 300));
		System.out.println(allWithOutgoingEdge);
	}

}
