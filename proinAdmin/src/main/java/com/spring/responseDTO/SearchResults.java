package com.spring.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResults {
    private List<String> results;
    private int noOfItems;
}
