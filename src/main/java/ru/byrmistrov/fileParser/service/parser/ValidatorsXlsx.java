package ru.byrmistrov.fileParser.service.parser;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.byrmistrov.fileParser.dto.PassportDataDtoPojo;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidatorsXlsx extends AnalysisEventListener<PassportDataDtoPojo> {
    private final Validator validator;
    private final ValidatorFactory validatorFactory;

    @Override
    public void invoke(PassportDataDtoPojo data, AnalysisContext context) {


        Integer rowIndex = context.readRowHolder().getRowIndex();
        Set<ConstraintViolation<PassportDataDtoPojo>> violations = validator.validate(data);
        if (!violations.isEmpty()) {
            throw new DataIntegrityViolationException("Следующие значение не являются валидной: " + " Ошибка в строке " + rowIndex + violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .toList());
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        try {
            validatorFactory.close();
        } catch (Exception e) {
            log.warn("Ошибка при закрытии ValidatorFactory", e);
        }
    }
}
