package com.riding.springboot.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends CommonResult {
    // Generic Interface에 <T>를 지정하여 어떤 형태의 값도 넣을 수 있도록 구현
    private List<T> list;
}
