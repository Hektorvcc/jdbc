package com.amigoscode.actor;

import com.amigoscode.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorDao actorDao;

    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public List<Actor> getActors() {
        return actorDao.selectActors();
    }

    public void addNewActor(Actor Actor) {
        // TODO: check if Actor exists
        int result = actorDao.insertActor(Actor);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteActor(Integer id) {
        Optional<Actor> Actors = actorDao.selectActorById(id);
        Actors.ifPresentOrElse(Actor -> {
            int result = actorDao.deleteActor(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete Actor");
            }
        }, () -> {
            throw new NotFoundException(String.format("Actor with id %s not found", id));
        });
    }

    public Actor getActor(int id) {
        return actorDao.selectActorById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Actor with id %s not found", id)));
    }
}
