package ca.sheridancollege.ca.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matrix implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int m;
	private int n;
	private String option;
	private String result;

}
