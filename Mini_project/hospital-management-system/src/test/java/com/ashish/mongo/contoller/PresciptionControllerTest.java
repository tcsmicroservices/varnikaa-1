package com.ashish.mongo.contoller;

import com.ashish.mongo.model.Prescription;
import com.ashish.mongo.repository.PrescriptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PresciptionControllerTest {

    @InjectMocks
    private PresciptionController presciptionController;

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @Test
    public void savePrescription() {
        Prescription mockPrescription = mock(Prescription.class);
        Prescription prescription= new Prescription();
        prescription.setPrescriptionId("presc1");
        prescription.setDescription("desc");
        prescription.setDoctorName("doctor1");
        prescription.setPatientName("patient1");
        prescription.setAppointmentId("appoint1");
        List<Prescription> prescriptionList = new ArrayList<>();
        prescriptionList.add(prescription);
        when(prescriptionRepository.save(any(Prescription.class))).thenReturn(prescription);
        Prescription res = presciptionController.savePrescription(mockPrescription);
        assertEquals(res.getPrescriptionId(),prescription.getPrescriptionId());
        assertEquals(res.getPatientName(),prescription.getPatientName());
        assertEquals(res.getDoctorName(),prescription.getDoctorName());
        assertEquals(res.getAppointmentId(),prescription.getAppointmentId());
        assertEquals(res.getDescription(),prescription.getDescription());
    }
    @Test
    public void getAllPrescriptions() {
        Prescription prescription= new Prescription();
        prescription.setPrescriptionId("presc1");
        prescription.setDescription("desc");
        prescription.setDoctorName("doctor1");
        prescription.setPatientName("patient1");
        prescription.setAppointmentId("appoint1");
        List<Prescription> prescriptionList = new ArrayList<>();
        prescriptionList.add(prescription);
        when(prescriptionRepository.findByPatientName(anyString())).thenReturn(prescriptionList);
        List<Prescription> res = presciptionController.getAllPrescriptions("patient1");
        assertEquals(res.get(0).getPrescriptionId(),prescriptionList.get(0).getPrescriptionId());
        assertEquals(res.get(0).getAppointmentId(),prescriptionList.get(0).getAppointmentId());
        assertEquals(res.get(0).getPatientName(),prescriptionList.get(0).getPatientName());
        assertEquals(res.get(0).getDoctorName(),prescriptionList.get(0).getDoctorName());
        assertEquals(res.get(0).getDescription(),prescriptionList.get(0).getDescription());
    }
}
