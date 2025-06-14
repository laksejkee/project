package ru.byrmistrov.fileParser.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import ru.byrmistrov.fileParser.dto.HasPassportNumber;
import ru.byrmistrov.fileParser.dto.PassportDataDto;
import ru.byrmistrov.fileParser.dto.PassportDataDtoPojo;
import ru.byrmistrov.fileParser.entity.PassportDataEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PassportDataMapper {
    @SubclassMapping(source = PassportDataDto.class, target = PassportDataEntity.class)
    @SubclassMapping(source = PassportDataDtoPojo.class, target = PassportDataEntity.class)
    PassportDataEntity dtoToEntity(HasPassportNumber dto);

    default List<PassportDataEntity> toEntity(List<? extends HasPassportNumber> dto) {
        return dto.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
