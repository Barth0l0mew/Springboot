package org.itstep.controller;

import org.itstep.model.Song;
import org.itstep.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class SongController {

    private final SongService songService;
    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }


    @GetMapping("/songs")
    public String showSongs(Model model) {
        List<Song> songs = songService.findAll();
        
songs.stream().forEach(System.out::println);
songs.stream().forEach(el-> System.out.println(el.getSongName()));
        model.addAttribute("songs", songs);

        return "songs";
    }
    @GetMapping("/add")
    public String addsong(@ModelAttribute("song") Song song){

        return "addsongs";
    }
    @PostMapping("/songs")
    public String createSong(@ModelAttribute("song") Song song,
                             @RequestParam("audioFile")MultipartFile audioFile,
                             @RequestParam("photoFile")MultipartFile photoFile) throws IOException {

        if (!audioFile.isEmpty()) {
            File dir = new File("C:/Users/gk/Springboot/Spring-sound/src/main/resources/static/songs");
            audioFile.transferTo(new File(dir.getAbsolutePath()+"/"+audioFile.getOriginalFilename()));
            song.setAudioFilePath("songs/"+audioFile.getOriginalFilename());
        }

        if (!photoFile.isEmpty()) {
            File dir = null; //Файловая система
            dir = new File("C:/Users/gk/Springboot/Spring-sound/src/main/resources/static/photos");
           // dir = new File("target/classes/static/photos");
            photoFile.transferTo(new File(dir.getAbsolutePath()+"/"+photoFile.getOriginalFilename()));
            song.setPhotoFilePath("photos/"+photoFile.getOriginalFilename());
        }

//System.out.println        (song);
        // Сохранение сущности Song в базу данных

        songService.save (song);
        return "redirect:/songs";

    }

} 