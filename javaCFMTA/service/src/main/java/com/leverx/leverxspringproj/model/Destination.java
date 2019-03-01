package com.leverx.leverxspringproj.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
public class Destination {
	
	private String name;
	private String description;
	private String destinationType;
	private List<Property> propertyList;
	
}