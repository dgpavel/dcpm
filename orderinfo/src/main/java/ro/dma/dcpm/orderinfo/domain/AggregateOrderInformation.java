package ro.dma.dcpm.orderinfo.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class AggregateOrderInformation {

    private Long id;
    private String customerName;
    private List<AggregateOrderPositionInformation> positions;
    private OrderState state;

    public void addOrderPositions(List<AggregateOrderPositionInformation> positionsToAdd) {
        if (positions == null) {
            positions = new ArrayList<>();
        }
        if (positionsToAdd != null) {
            this.positions.addAll(positionsToAdd);
        }
    }
}