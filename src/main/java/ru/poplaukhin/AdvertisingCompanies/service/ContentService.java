package ru.poplaukhin.AdvertisingCompanies.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.poplaukhin.AdvertisingCompanies.entity.Content;
import ru.poplaukhin.AdvertisingCompanies.repository.ContentRepository;
import ru.poplaukhin.AdvertisingCompanies.response.EntityResponse;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContentService {
    private final ContentRepository repository;
    private final Logger log = LoggerFactory.getLogger(Content.class);

    public List<Content> getAll() {
        log.info("get all");
        return repository.findAll();
    }

    public EntityResponse<Content> getContentById(Integer id) {
        log.info("get content by id " + id);
        Content content = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Контента не найдено с таким id " + id));

        return new EntityResponse<>(true, content);
    }

    @Transactional
    public void save(Content content) {
        Hibernate.initialize(content.getCompaign());

        log.info("save in content " + content);
        repository.save(content);
    }

    @Transactional
    public Content update(Integer id, Content contentUpdate) {
        Hibernate.initialize(contentUpdate.getCompaign());

        Optional<Content> oldContent = repository.findById(id);
        log.info("update in content " + oldContent);

        if (oldContent.isPresent()) {
            oldContent.get().setContentType(contentUpdate.getContentType());
            oldContent.get().setContentUrl(contentUpdate.getContentUrl());
            oldContent.get().setCompaign(contentUpdate.getCompaign());

            repository.save(oldContent.get());
        }

        return oldContent.orElse(null);
    }

    public void drop(Integer id) {
        Content content = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Контента не найдено с таким id " + id));
        log.info("drop in content " + content);

        repository.delete(content);
    }
}
