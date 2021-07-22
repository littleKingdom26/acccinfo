package info.team23h.acc.service.video;

import info.team23h.acc.repository.bbs.BbsNameRepository;
import info.team23h.acc.repository.bbs.BbsRepository;
import info.team23h.acc.repository.file.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoServiceImpl implements VideoService{

	final private BCryptPasswordEncoder bCryptPasswordEncoder;

	final private BbsNameRepository bbsNameRepository;

	final private BbsRepository bbsRepository;

	final private FileRepository fileRepository;

}
