package ch.presentium.backend.business.repository;

import ch.presentium.backend.business.model.schedule.SchoolClass;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    @Query(
        """
          select distinct sc
          from SchoolClass sc
            left join sc.students s
            left join sc.teacher t
          where lower(s.fullName) like '%' || lower(:name) || '%'
             or lower(t.fullName) like '%' || lower(:name) || '%'
        """
    )
    List<SchoolClass> findByStudentOrTeacherName(String name);
}
