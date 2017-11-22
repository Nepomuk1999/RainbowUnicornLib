package at.fhv.team3.domain;

import at.fhv.team3.domain.dto.DTO;
import at.fhv.team3.domain.dto.OverdueNoticeDTO;
import at.fhv.team3.domain.interfaces.Transferable;

import javax.persistence.*;
import java.util.HashMap;

/**
 * Created by Christoph on 13.11.2017.
 */
@Entity
@Table(name = "overdueNotice")
public class OverdueNotice implements Transferable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _noticeId;

    @OneToOne
    @JoinColumn(name = "borrowedItem_borrowedId")
    private BorrowedItem _borrowedItem;

    public DTO createDataTransferObject() {
            return new OverdueNoticeDTO(_noticeId, _borrowedItem.getBorrowedId());
    }

    public void fillFromDTO(DTO dto) {
        HashMap<String, String> allData = dto.getAllData();
        _noticeId = Integer.parseInt(allData.get("noticeId"));
        //_borrowedItem = allData.get("borrowedItem");
    }
}
