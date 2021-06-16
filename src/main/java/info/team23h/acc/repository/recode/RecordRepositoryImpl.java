package info.team23h.acc.repository.recode;

import info.team23h.acc.entity.recode.Record;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class RecordRepositoryImpl extends QuerydslRepositorySupport implements RecordRepositoryCustom {
	/**
	 * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 */
	public RecordRepositoryImpl() {
		super(Record.class);
	}

}
