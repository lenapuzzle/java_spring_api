//package com.launchacademy.marathon.controllers;

//import com.launchacademy.marathon.models.*;
//import com.launchacademy.marathon.services.*;
//import lombok.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.data.domain.*;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//import javax.persistence.criteria.*;
//import java.util.*;
//
//@RestController
//@RequestMapping("/api/v1/songs")
//public class SongApiV1Controller {
//  private SongService songService;
//
//  @Autowired
//  public SongApiV1Controller(SongService songService) {
//    this.songService = songService;
//  }
//
//  @GetMapping
//  public Map<String, Iterable<Song>> getSongsApi(Pageable pageable) {
//    Map<String, Iterable<Song>> map = new HashMap<>();
//    map.put("songs", songService.findAll(pageable));
//    return map;
//  }
//
//  @GetMapping("{id}")
//  public Song getSongleSong(@PathVariable Integer id) {
//    return songService.findById(id).orElseThrow(() -> new SongNotFoundException());
//  }
//
//  // src/main/java/com.launchacademy.consumelater/controllers/UrlsApiV1Controller.java
//  @NoArgsConstructor
//  private class SongNotFoundException extends RuntimeException {};
//
//  @ControllerAdvice
//  private class SongNotFoundAdvice {
//    @ResponseBody
//    @ExceptionHandler(SongNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    String songNotFoundHandler(SongNotFoundException ex) {
//      return ex.getMessage();
//    }
//  }
//}


package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.services.SongService;
import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/songs")
public class SongApiV1Controller {
  private SongService songService;

  @Autowired
  public SongApiV1Controller(SongService songService) {
    this.songService = songService;
  }

  @GetMapping
  public Map<String, Iterable<Song>> getAllSongs(Pageable pageable) {
    Map<String, Iterable<Song>> allSongs = new HashMap<>();
    allSongs.put("songs", this.songService.findAll(pageable));
    return allSongs;
  }

  @GetMapping("/{id}")
  public Song getOneSong(@PathVariable Integer id) {
    return songService.findById(id).orElseThrow(()->new SongNotFoundException());
  }

  @NoArgsConstructor
  private class SongNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class SongNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(SongNotFoundException ex) {
      return ex.getMessage();
    }
  }
}