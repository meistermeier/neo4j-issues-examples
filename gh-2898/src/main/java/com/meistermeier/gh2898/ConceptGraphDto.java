package com.meistermeier.gh2898;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConceptGraphDto {
    private Concept  concept;
    private List<CCLink> ccLinks;
    private List<Concept> relatedConcepts;
}