package ru.byrmistrov.fileParser.servicejob;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.byrmistrov.fileParser.repository.PassportRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@EnableCaching
@RequiredArgsConstructor
public class NumberCache {
    private final PassportRepository passportRepository;

    @Transactional
    @Cacheable("passportNumbers")
    public Set<String> getAllPassportNumbers() {
        return passportRepository.allPassportNumbers()
                .collect(Collectors.toSet());
    }
}
