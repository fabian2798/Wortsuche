package com.example.Wortsuche;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/Vorlagen")
public class VorlagenController {
    @Autowired
    private VorlagenService vorlagenService;
    @GetMapping
    public ResponseEntity<List<Vorlagen>> getAllVorlagen (){
        return new ResponseEntity<List<Vorlagen>>(vorlagenService.allVorlagen(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vorlagen>> getsingleVorlage(@PathVariable ObjectId id){
        Optional<Vorlagen> vorlage = vorlagenService.singleVorlage(id);
        if(vorlage.isPresent()) {
            return new ResponseEntity<Optional<Vorlagen>>(vorlagenService.singleVorlage(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/ByTitle")
    public ResponseEntity<Optional<Vorlagen>> getsingleVorlageByTitle(@RequestParam String title){
        Optional<Vorlagen> vorlage = vorlagenService.singleVorlageByTitle(title);
        if(vorlage.isPresent()) {
            return new ResponseEntity<Optional<Vorlagen>>(vorlage, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
// The URL that returns "API is working." is localhost:8080/api/apitest 
    @GetMapping("/apitest")
    public ResponseEntity<String> apitest(){
        return new ResponseEntity<String>("API is working.", HttpStatus.OK);
    }
    
    @GetMapping("/foundwords/{version}")
    public ResponseEntity<Map<String, List<Object>>> getFoundWords(@PathVariable String version) {
        List<Vorlagen> allVorlagen = vorlagenService.allVorlagen();

        int vorlagenIndex;
        switch (version) {
            case "v1":
                vorlagenIndex = 0;
                break;
            case "v2":
                vorlagenIndex = 1;
                break;
            case "v3":
                vorlagenIndex = 2;
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // out of bound
        if (vorlagenIndex >= allVorlagen.size()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Map<String, List<Object>> map = vorlagenService.findWordsinVorlagen(allVorlagen.get(vorlagenIndex));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/createVorlagen")
    public ResponseEntity<Vorlagen> createVorlage(){
        /**
         * TODO: endpoint for new creations of vorlagen
         */
        return new ResponseEntity<Vorlagen>(HttpStatus.CREATED);
    }

}
