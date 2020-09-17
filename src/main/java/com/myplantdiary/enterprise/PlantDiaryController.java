package com.myplantdiary.enterprise;

import com.myplantdiary.enterprise.dto.Specimen;
import com.myplantdiary.enterprise.service.ISpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The controller fro Plant Diary REST endpoints and web UI
 * <p>
 * This class handles the CRUD operations for My Plant Diary specimens, via HTTP actions.
 * </p>
 * <p>
 * This class also serves HTML based web pages, for UI interactions with plant specimens.
 * </p>
 * @author Brandan Jones
 */
@Controller
public class PlantDiaryController {

    @Autowired
    ISpecimenService specimenService;

    /**
     * Handle the root (/) endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @GetMapping("/specimen")
    @ResponseBody
    public List<Specimen> fetchAllSpecimens() {
        return specimenService.fetchAll();
    }

    /**
     * Fetch a specimen with the given ID.
     *
     * Given the parameter id, find a specimen that corresponds to this unique ID.
     *
     * Returns one of the following status codes:
     * 200: specimen found
     * 400: specimen not found
     *
     * @param id a unique identifier for this specimen
     */
    @GetMapping("/specimen/{id}/")
    public ResponseEntity fetchSpecimenById(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Create a new specimen object, given the data provided.
     *
     * returns one of the following status codes:
     * 201: successfully created a new specimen.
     * 409: unable to create a specimen, because it already exists.
     *
     * @param specimen a JSON representation of a specimen object.
     * @return the newly created specimen object.
     */
    @PostMapping(value="/specimen", consumes="application/json", produces="application/json")
    public Specimen createSpecimen(@RequestBody Specimen specimen) {
        Specimen newSpecimen = null;
        try {
            newSpecimen = specimenService.save(specimen);
        } catch (Exception e) {
            // TODO add logging
        }
        return newSpecimen;
    }

    @DeleteMapping("/specimen/{id}/")
    public ResponseEntity deleteSpecimen(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.OK);
    }


}
