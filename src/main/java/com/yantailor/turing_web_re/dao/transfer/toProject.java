package com.yantailor.turing_web_re.dao.transfer;

import com.yantailor.turing_web_re.entity.Project;
import com.yantailor.turing_web_re.entity.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by yantailor
 * on 2022/3/17 11:22 @Version 1.0
 */
@Mapper
public interface toProject {
    toProject INSTANCE = Mappers.getMapper(toProject.class);

    Project toProject(ProjectDto projectDto);

    ProjectDto toProjectDto(Project project);
}
