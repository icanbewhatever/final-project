package com.cnr_furniture.controller;

import com.cnr_furniture.domain.Machine.*;
import com.cnr_furniture.service.machine.MachineMNGService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.extern.log4j.Log4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j
public class MachineMNGController {

    @Autowired
    private final MachineMNGService machineMNGService;
    @Autowired
    public MachineMNGController(MachineMNGService machineMNGService) {
        this.machineMNGService = machineMNGService;
    }

    /**
     * Desc: 설비관리체크기준관리 - 체크리스트 리스트 가져오기 & 검색
     * @return: machine/machineCheckInfo
     */
    @GetMapping("/M/machine/machineCheckInfo")
    public String machineCheckInfo(SearchMachineCheckVO searchMachineCheckVO, Model model) {
        // 체크리스트 가져오기
        List<MachineCheckVO> machineCheckVOList = machineMNGService.getMachineCheckList(searchMachineCheckVO);
        model.addAttribute("machineCheckVOList", machineCheckVOList);

        // 설비 유형 가져오기
        List<MachineCheckVO> getMachineCheckType = machineMNGService.getMachineCheckType();
        model.addAttribute("getMachineCheckType", getMachineCheckType);

        // 점검 방법 가져오기
        List<MachineCheckVO> getMachineCheckMethod = machineMNGService.getMachineCheckMethod();
        model.addAttribute("getMachineCheckMethod", getMachineCheckMethod);

        // 검색 keyword
        model.addAttribute("searchMachineCheckVO", searchMachineCheckVO);

        return "machine/machineCheckInfo";
    }

    /**
     * Desc: 설비관리체크기준관리 - 모달에서 체크리스트 등록
     * @return: machine/machineCheckInfo
     */
    @PostMapping("/M/machine/machineCheckInfo")
    @ResponseBody
    public MachineCheckVO insertMachineCheck(
            @RequestBody MachineCheckVO machineCheckVO,
            RedirectAttributes rttn
    ){
        // 실제 DB에 텍스트 데이터 저장
        int rtn = machineMNGService.insertMachineCheck(machineCheckVO);
        rttn.addFlashAttribute("insertSuccessCount", rtn);

        // DB에서 입력했던 정보를 바탕으로 DB에서 추가한 설비정보 데이터를 가져온다.
        MachineCheckVO machineCheckVOOne = machineMNGService.getMachineCheckOne();

        // 위에서 가져온 데이터를 아래 vo에 맞게 set한다.
        MachineCheckVO machineCheckVOone = new MachineCheckVO();
        machineCheckVOOne.setMci_id(machineCheckVOOne.getMci_id());
        machineCheckVOOne.setMci_div(machineCheckVOOne.getMci_div());
        machineCheckVOOne.setMci_method(machineCheckVOOne.getMci_method());
        machineCheckVOOne.setMci_condition(machineCheckVOOne.getMci_condition());

        return machineCheckVO;

    }

    /**
     * Desc: 설비 체크리스트 작성 - 선택한 설비명의 설비 ID에 맞는 체크리스트 보여주기
     * @return: machine/machineCheck
     */
    @GetMapping("/machine/machineCheck")
    public String machineCheck(SearchMachineVO searchMachineVO, Model model) {

        // 설비 ID와 이름 가져오기
        List<MachineVO> getMachineInfo = machineMNGService.getMachineInfo();
        model.addAttribute("getMachineInfo", getMachineInfo);

        // 리스트 가져오기
        List<MachineCheckVO> getMachineCheckAll;
        if (searchMachineVO != null && searchMachineVO.getFind_machine_id() != null) {
            getMachineCheckAll = machineMNGService.getMachineCheckAll(searchMachineVO);
        } else {
            // 선택된 설비가 없는 경우 빈 리스트 반환
            getMachineCheckAll = new ArrayList<>();
        }
        model.addAttribute("getMachineCheckAll", getMachineCheckAll);

        // 이 코드를 쓰면 설비를 선택하지 않았을 때 설비 전체가 나옴
//        List<MachineCheckVO> getMachineCheckAll = machineMNGService.getMachineCheckAll(searchMachine);
//        model.addAttribute("getMachineCheckAll", getMachineCheckAll);

        return "machine/machineCheck";
    }

    /**
     * Desc: 설비 체크리스트 작성 - 체크리스트 기록 추가
     * 체크리스트 답변에 따라 설비 상태와 설비 가동 현황 업데이트
     * @return: machine/machineCheck
     */
    @PostMapping("/machine/machineCheckAdd")
    @ResponseBody
    public ResponseEntity<MachineCheckRecordVO> machineCheckRecord(@RequestBody MachineCheckRecordVO machineCheckRecordVO,
                                                                   RedirectAttributes rttr) {
        // 체크리스트 기록 추가
        int rtn = machineMNGService.insertMachineCheckRecord(machineCheckRecordVO);
        rttr.addFlashAttribute("insertSuccessCount", rtn);

        // 체크리스트 답변에 따라 업데이트 실행
        String newCondition = machineCheckRecordVO.getMcr_answer();
        int newMI_ID = machineCheckRecordVO.getMcr_mi_id();

        if (newCondition.equals("Y")) {
            // 설비 상태와 설비 가동 현황 업데이트
            String newStatus = machineCheckRecordVO.getMcr_answer();
            int updateSuccessCount = machineMNGService.updateMachineCondition(newCondition, newStatus, newMI_ID);
            rttr.addFlashAttribute("updateSuccessCount", updateSuccessCount);
        } else if (newCondition.equals("N")) {
            // 설비 상태 업데이트
            int updateMcSuccessCount = machineMNGService.updateMcWork(newCondition, newMI_ID);
            rttr.addFlashAttribute("updateSuccessCount", updateMcSuccessCount);
        }

        return ResponseEntity.ok(machineCheckRecordVO);
    }

    /**
     * Desc: 설비수리 이력 관리 - 설비수리 이력 관리 리스트 & 검색
     * @return: machine/machineRepair
     */
    @GetMapping("/M/machine/machineRepair")
    public String machineRepair(SearchMachineVO searchMachineVO, Model model){
        List<MachineRepairVO> machineRepairVOList = machineMNGService.McRepairList(searchMachineVO);
        model.addAttribute("machineRepairVOList", machineRepairVOList);

        // 검색 keyword
        model.addAttribute("searchMachineVO", searchMachineVO);

        return "machine/machineRepair";
    }



}
