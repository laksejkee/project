package ru.byrmistrov.fileParser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.byrmistrov.fileParser.entity.PassportDataEntity;

import java.util.stream.Stream;

@Repository
public interface PassportRepository extends JpaRepository<PassportDataEntity, Long> {
    @Query("SELECT p.passportNumber FROM PassportDataEntity p")
    Stream<String> streamAllPassportNumbers();
}
