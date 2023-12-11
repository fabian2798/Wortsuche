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
        return new ResponseEntity<Optional<Vorlagen>>(vorlagenService.singleVorlage(id), HttpStatus.OK);
    }

    @GetMapping("/byTitle")
    public ResponseEntity<Optional<Vorlagen>> getsingleVorlageByTitle(@RequestParam String title){
        return new ResponseEntity<Optional<Vorlagen>>(vorlagenService.singleVorlageByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/foundwords/v1")
    public ResponseEntity<Map<String, List<Object>>> getfoundWordsv1 () {
        List<Vorlagen> allVorlagen = vorlagenService.allVorlagen();
        Map<String, List<Object>> map = vorlagenService.findWordsinVorlagen(allVorlagen.get(0));
        return new ResponseEntity<Map<String, List<Object>>>(map, HttpStatus.OK);
    }

    @GetMapping("/foundwords/v2")
    public ResponseEntity<Map<String, List<Object>>> getfoundWordsv2 () {
        List<Vorlagen> allVorlagen = vorlagenService.allVorlagen();
        Map<String, List<Object>> map = vorlagenService.findWordsinVorlagen(allVorlagen.get(1));
        return new ResponseEntity<Map<String, List<Object>>>(map, HttpStatus.OK);
    }

    @GetMapping("/foundwords/v3")
    public ResponseEntity<Map<String, List<Object>>> getfoundWordsv3 () {
        List<Vorlagen> allVorlagen = vorlagenService.allVorlagen();
        Map<String, List<Object>> map = vorlagenService.findWordsinVorlagen(allVorlagen.get(2));
        return new ResponseEntity<Map<String, List<Object>>>(map, HttpStatus.OK);
    }
}
