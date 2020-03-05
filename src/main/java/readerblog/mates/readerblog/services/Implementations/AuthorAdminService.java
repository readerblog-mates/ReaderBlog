package readerblog.mates.readerblog.services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.repositories.AuthorAdminRepository;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorAdminService {
    private  final AuthorAdminRepository authorAdminRepository;

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
