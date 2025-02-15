package ae.pegasus.framework.controllers.records.all_page;

import ae.pegasus.framework.blocks.context.tables.Row;
import ae.pegasus.framework.blocks.context.tables.records.RecordAllRow;
import ae.pegasus.framework.controllers.records.RecordsTableController;
import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.model.Record;

public class RecordsTableAllController extends RecordsTableController {

    public RecordsTableAllController(SigintPage page) {
        super(page);
    }


    @Override
    protected Record initFromRow(Row recordRow) {
        RecordAllRow row = (RecordAllRow)recordRow;
        Record newRecord = super.initFromRow(recordRow);
        newRecord.setSourceName(row.getSource());
        return newRecord;
    }


}
