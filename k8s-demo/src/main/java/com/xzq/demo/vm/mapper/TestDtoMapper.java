package com.xzq.demo.vm.mapper;

import com.xzq.demo.domain.Test;
import com.xzq.demo.vm.TestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestDtoMapper extends BaseMapper<TestDto,Test> {

}
