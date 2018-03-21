package org.cerberus.servlet.crud.interactivetuto;

import org.apache.commons.collections.ListUtils;
import org.cerberus.crud.entity.InteractiveTuto;
import org.cerberus.crud.entity.InteractiveTutoStep;
import org.cerberus.crud.service.impl.InteractiveTutoService;
import org.cerberus.dto.InteractiveTutoDTO;
import org.cerberus.dto.InteractiveTutoStepDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interactiveTuto")
public class InteractiveTutoController {
    @Autowired
    private InteractiveTutoService interactiveTutoService;

    @RequestMapping("/get")
    public ResponseEntity<InteractiveTutoDTO> getInteractiveTuto(int id) {

        InteractiveTuto it = interactiveTutoService.getInteractiveTutorial(id, true, "fr");

        if (it == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // TODO create a converter
        InteractiveTutoDTO result = new InteractiveTutoDTO(it.getId(), it.getTitle(), it.getDescription(), it.getRole(), it.getOrder(), it.getLevel().getValue());
        if (!CollectionUtils.isEmpty(it.getSteps())) {
            result.setSteps(new LinkedList<>());
            for (InteractiveTutoStep step : it.getSteps()) {
                result.getSteps().add(new InteractiveTutoStepDTO(step.getId(), step.getSelectorJquery(), step.getText(), step.getType(), step.getAttr1()));
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/list")
    public ResponseEntity<List<InteractiveTutoDTO>> getListInteractiveTuto() {

        List<InteractiveTuto> it = interactiveTutoService.getListInteractiveTutorial(false, "fr");

        if (CollectionUtils.isEmpty(it)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(listInteractiveTuto(it), HttpStatus.OK);
    }


    private InteractiveTutoDTO convertInteractiveTuto(InteractiveTuto it) {
        InteractiveTutoDTO result = new InteractiveTutoDTO(it.getId(), it.getTitle(), it.getDescription(), it.getRole(), it.getOrder(), it.getLevel().getValue());
        if (!CollectionUtils.isEmpty(it.getSteps())) {
            result.setSteps(new LinkedList<>());
            for (InteractiveTutoStep step : it.getSteps()) {
                result.getSteps().add(new InteractiveTutoStepDTO(step.getId(), step.getSelectorJquery(), step.getText(), step.getType(), step.getAttr1()));
            }
        }
        return result;
    }

    private List<InteractiveTutoDTO> listInteractiveTuto(List<InteractiveTuto> itlist) {
        return itlist.stream().map(it -> convertInteractiveTuto(it)).collect(Collectors.toList());
    }
}
