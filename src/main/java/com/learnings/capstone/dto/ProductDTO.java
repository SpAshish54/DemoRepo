package com.learnings.capstone.dto;

public class ProductDTO {

    private String name;

    private String internalName;

    // Assuming that the product ID is generated on the server side
    // and should not be set manually in the DTO
    // private Long productId;

    private String details;

    private Integer maxProductsPerLocation;

    // Getter and Setter methods for each field

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    // Add similar getter and setter methods for other fields

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getMaxProductsPerLocation() {
        return maxProductsPerLocation;
    }

    public void setMaxProductsPerLocation(Integer maxProductsPerLocation) {
        this.maxProductsPerLocation = maxProductsPerLocation;
    }
}

