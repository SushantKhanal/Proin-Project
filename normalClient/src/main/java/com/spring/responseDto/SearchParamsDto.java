package com.spring.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchParamsDto {
    private String searchThis;
    private String country;
}
