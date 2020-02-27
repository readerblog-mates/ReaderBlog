package readerblog.mates.readerblog.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.repositories.AuthorAdminRepository;

import java.util.List;

@Service
public class AuthorAdminService {
    private AuthorAdminRepository authorAdminRepository;

    @Autowired
    public void setAuthorAdminRepository(AuthorAdminRepository authorAdminRepository) {
        this.authorAdminRepository = authorAdminRepository;
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) authorAdminRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorAdminRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        authorAdminRepository.deleteById(id);
    }

    public Author save(Author author) {
        return authorAdminRepository.save(author);
    }


}
