package com.school.manager.common.req;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 分页查询请求对象
 */
@Data
@NoArgsConstructor
public class PageQuery implements Serializable {
    private static final long serialVersionUID = 3758797195343039255L;
    private Long pageSize;
    private Long pageNo;
}
