package org.scoula.sample.service;


import org.springframework.stereotype.Service;

@Service
public class SampleServiceImplTest implements SampleService {

    @Override
    public Integer doAdd(String str1, String str2) throws Exception {
        return Integer.parseInt(str1) + Integer.parseInt(str2);
    }
}
