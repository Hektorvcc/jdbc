package com.amigoscode.actor;

import com.amigoscode.movie.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/actors")
public class ActorController {

    private final ActorService actorService ;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> listActors() {
        return actorService.getActors();
    }

    @GetMapping("{id}")
    public Actor getActorId(@PathVariable("id") Integer id) {
        return actorService.getActor(id);
    }

    @PostMapping
    public void addActor(@RequestBody Actor Actor) {
        actorService.addNewActor(Actor);
    }

    @DeleteMapping("{id}")
    public void deleteActor(@PathVariable("id") Integer id) {
        actorService.deleteActor(id);
    }

    // TODO: Update Actor

}
