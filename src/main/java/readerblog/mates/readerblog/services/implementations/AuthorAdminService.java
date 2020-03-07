package readerblog.mates.readerblog.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.Author;
import readerblog.mates.readerblog.repositories.AuthorAdminRepository;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorAdminService {
    private final AuthorAdminRepository authorAdminRepository;

    @Transactional
    public List<Author> getAllAuthors() {
        return (List<Author>) authorAdminRepository.findAll();
    }

    @Transactional
    public Author getAuthorById(Long id) {
        return authorAdminRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Long id) {
        authorAdminRepository.deleteById(id);
    }

    @Transactional
    public Author save(Author author) {
        return authorAdminRepository.save(author);
    }
}
