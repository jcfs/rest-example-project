package pt.ef.backback.core.service;

import org.apache.log4j.Logger;

/**
 * @author jsalavisa
 */
public class GenericServiceImpl implements GenericService {

	final static Logger logger = Logger.getLogger(GenericServiceImpl.class);
	
	/**
	 * (non-Javadoc)
	 * @see pt.ef.backback.core.service.GenericService#getGenericMethod()
	 */
	public long getGenericMethod() {
		logger.info("GenericServiceImpl.getGenericMethod invoked");
		return 0;
	}

}