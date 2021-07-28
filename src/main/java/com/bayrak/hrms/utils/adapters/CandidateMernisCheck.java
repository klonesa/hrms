package com.bayrak.hrms.utils.adapters;

import com.bayrak.hrms.utils.adapters.abstracts.MernisCheck;
import com.bayrak.hrms.model.Candidate;
import com.bayrak.hrms.utils.mernis.RABKPSPublicSoap;
import org.springframework.stereotype.Service;

@Service
public class CandidateMernisCheck implements MernisCheck<Candidate> {
    @Override
    public boolean check(Candidate data) {

        RABKPSPublicSoap client = new RABKPSPublicSoap();
        try {
            return client.TCKimlikNoDogrula(
                    Long.valueOf(
                            data.getIdentityNumber()),
                    data.getFirstName(),
                    data.getLastName(),
                    data.getBirthYear());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
