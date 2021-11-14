package fr.weeab.mangalist.core.context;


import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public abstract class AbstractRepositoryTest extends AbstractAppContextTest { }
