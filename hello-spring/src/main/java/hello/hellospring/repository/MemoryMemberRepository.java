package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();   // 원래는 동시성 문제 때문에 공유되는 변수일 때는 concurrent를 써야 함.
    private static long sequence = 0L;  // 원래는 동시성 문제 때문에 atomic 등을 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   // sequence를 올려줌
        store.put(member.getId(), member);  // member에 id를 세팅
        return member;  // store에 넣기 전에 멤버에 id값을 세팅해주고 이름은 save 하기 전에 넘어온 상태
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
