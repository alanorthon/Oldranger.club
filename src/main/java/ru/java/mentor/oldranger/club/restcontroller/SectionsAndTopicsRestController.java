package ru.java.mentor.oldranger.club.restcontroller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.java.mentor.oldranger.club.controller.SectionsAndTopicsController;
import ru.java.mentor.oldranger.club.dto.SectionsAndTopicsDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class SectionsAndTopicsRestController {

    private SectionsAndTopicsController sectionsAndTopicsController;

    @RequestMapping("/sectionsandactualtopics")
    public ResponseEntity<List<SectionsAndTopicsDto>> getSectionsAndTopicsDto() {
        List<SectionsAndTopicsDto> dtos = sectionsAndTopicsController.getAllSectionsAndActualTopicsLimit10BySection();
        return ResponseEntity.ok(dtos);
    }
}
