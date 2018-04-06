package com.spring.requestDto;

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
public class ApproveFollowRequestInfo {
    private String followedBy;
    private String following;
}
