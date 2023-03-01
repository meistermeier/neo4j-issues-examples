package com.example.gh2680;

import org.springframework.data.neo4j.core.schema.DynamicLabels;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node({"材料","Material"})
public class Material {

    @Id
    @GeneratedValue
    private Long materialId;

    @Property(name = "label")
    private String materialName;

    @Property(name = "英文名")
    private String englishName;

    @Property(name = "别称")
    private String nickName;

    @Property(name = "英文别称")
    private String englishNickName;

    @Property(name = "相对分子质量")
    private String relativeMolecularMass;

    @Property(name = "化学结构式")
    private String structuralFormula;

    @Property(name = "CAS-登记号")
    private String casRegistryNumber;

    @Property(name = "描述")
    private String materialDescription;

    @Property(name = "外观")
    private String materialAppearance;

    @Property(name = "用途")
    private List<String> materialUsage;

    @DynamicLabels
    private Set<String> materialLabels;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEnglishNickName() {
        return englishNickName;
    }

    public void setEnglishNickName(String englishNickName) {
        this.englishNickName = englishNickName;
    }

    public String getRelativeMolecularMass() {
        return relativeMolecularMass;
    }

    public void setRelativeMolecularMass(String relativeMolecularMass) {
        this.relativeMolecularMass = relativeMolecularMass;
    }

    public String getStructuralFormula() {
        return structuralFormula;
    }

    public void setStructuralFormula(String structuralFormula) {
        this.structuralFormula = structuralFormula;
    }

    public String getCasRegistryNumber() {
        return casRegistryNumber;
    }

    public void setCasRegistryNumber(String casRegistryNumber) {
        this.casRegistryNumber = casRegistryNumber;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getMaterialAppearance() {
        return materialAppearance;
    }

    public void setMaterialAppearance(String materialAppearance) {
        this.materialAppearance = materialAppearance;
    }

    public List<String> getMaterialUsage() {
        return materialUsage;
    }

    public void setMaterialUsage(List<String> materialUsage) {
        this.materialUsage = materialUsage;
    }

    public Set<String> getMaterialLabels() {
        return materialLabels;
    }

    public void setMaterialLabels(Set<String> materialLabels) {
        this.materialLabels = materialLabels;
    }

    public Material() {
    }

    public Material(Long materialId, String materialName, String englishName, String nickName, String englishNickName, String relativeMolecularMass, String structuralFormula, String casRegistryNumber, String materialDescription, String materialAppearance, List<String> materialUsage, Set<String> materialLabels) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.englishName = englishName;
        this.nickName = nickName;
        this.englishNickName = englishNickName;
        this.relativeMolecularMass = relativeMolecularMass;
        this.structuralFormula = structuralFormula;
        this.casRegistryNumber = casRegistryNumber;
        this.materialDescription = materialDescription;
        this.materialAppearance = materialAppearance;
        this.materialUsage = materialUsage;
        this.materialLabels = materialLabels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(materialId, material.materialId) && Objects.equals(materialName, material.materialName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, materialName);
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", materialName='" + materialName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", englishNickName='" + englishNickName + '\'' +
                ", relativeMolecularMass='" + relativeMolecularMass + '\'' +
                ", structuralFormula='" + structuralFormula + '\'' +
                ", casRegistryNumber='" + casRegistryNumber + '\'' +
                ", materialDescription='" + materialDescription + '\'' +
                ", materialAppearance='" + materialAppearance + '\'' +
                ", materialUsage=" + materialUsage +
                '}';
    }
}
