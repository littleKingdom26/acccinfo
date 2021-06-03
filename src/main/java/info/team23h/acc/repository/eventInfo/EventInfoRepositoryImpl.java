package info.team23h.acc.repository.eventInfo;

import info.team23h.acc.entity.event.EventInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class EventInfoRepositoryImpl extends QuerydslRepositorySupport implements EventInfoRepositoryCustom {
	/**
	 * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 */
	public EventInfoRepositoryImpl( ) {
		super(EventInfo.class);
	}
}
