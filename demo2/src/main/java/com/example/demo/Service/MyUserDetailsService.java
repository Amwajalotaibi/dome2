package com.example.demo.Service;

import com.example.demo.Model.MyUser;
import com.example.demo.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;
// لو ابيه يسجل بالايميل ما نغير اسم الولد يوسر ياي يوسزر نيم اللي يتغيل السترنق نحط ايميل

    @Override // تروح تشيك على اليوسر نيم هل هو موجود او لا
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=authRepository.findMyUserByUsername(username); //الفايند ما يتغير بس اللي بين الاقواس نحط فيه الايميل

        if(username==null){ // لو هو مو موجود نرمني لها ثرو
            throw new UsernameNotFoundException("Worng username or passwprd");
        }
        return myUser; // اذا موجوده ترجعه لي
    }
}
