package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository{

    @Override
    Optional<Member> findByName(String name);
}
