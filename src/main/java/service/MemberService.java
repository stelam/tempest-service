package service;

import com.tempest.service.model.Member;
import com.tempest.service.model.Role;
import com.tempest.service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by stelam on 2017-11-14.
 */
@Service("userDetailsService")
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByUsername( s );
        if ( member.isPresent() ) {
            return member.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    public Member findMemberByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByUsername( username );
        if ( member.isPresent() ) {
            return member.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }

    public Member registerMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        //member.grantAuthority(Role.ROLE_USER);
        return memberRepository.save( member );
    }

    @Transactional // To successfully remove the date @Transactional annotation must be added
    public void removeAuthenticatedMember() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Member  member = findMemberByUsername(username);
        memberRepository.deleteMemberById(member.getId());

    }
}
