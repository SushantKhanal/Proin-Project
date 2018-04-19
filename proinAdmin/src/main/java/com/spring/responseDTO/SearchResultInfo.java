package com.spring.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchResultInfo implements Serializable{
    private String username;
    private String tags;
}
