package com.example.endofgame.service;

import com.example.endofgame.converter.CategoryConverter;
import com.example.endofgame.dto.CategorySummary;
import com.example.endofgame.entity.Category;
import com.example.endofgame.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;

    private final CategoryConverter converter;

    public CategoryService(final CategoryRepository repository, final CategoryConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<CategorySummary> readAllCategories() {
        log.info("reading all categories");

        // logging levels:
        // trace
        // debug
        // info - default level
        // warn
        // error
        var result =  repository.findAll()
                .stream()
//                .map(category -> converter.fromEntityToDto(category))
                .map(converter::fromEntityToDto)
                .toList();
        log.info("number of read categories: [{}]", result.size());
        log.debug("result: {}", result);
//        log.info(String.format("number of read categories: [%s]", result.size()));

        return result;
    }

    // from Kotlin
    // String one =
    // String? two =
    // Optional<CategorySummary>
    public Optional<CategorySummary> readCategoryById(Long myId) {
        var result = repository.findById(myId);
        log.info("item with id: [{}] exists? - [{}]", myId, result.isPresent());
        log.debug("received category: [{}]", result.orElse(null));
        return result.map(category -> converter.fromEntityToDto(category));
    }

    public CategorySummary createNewCategory(CategorySummary newCategory) {
        Category toSave = converter.fromDtoToEntity(newCategory);
        Category saved = repository.save(toSave);

        log.info("creating new category");
        log.info("object before conversion: [{}]", newCategory);
        log.info("object after conversion: [{}]", toSave);
        log.info("saved object: [{}]", saved);

        return converter.fromEntityToDto(saved);
    }

    // TODO: fix the problem with non existent id
    @Transactional
    public void deleteCategoryById(Long idOfCategoryToDelete) {
        log.info("deleting category with id: [{}]", idOfCategoryToDelete);

        if (repository.existsById(idOfCategoryToDelete)) {
            repository.deleteById(idOfCategoryToDelete);
        }
    }
}
