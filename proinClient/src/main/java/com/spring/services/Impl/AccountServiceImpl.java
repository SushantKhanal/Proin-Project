package com.spring.services.Impl;

import com.spring.model.*;
import com.spring.repository.*;
import com.spring.responseDto.UserDocInfo;
import com.spring.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfilePicRepository userProfilePicRepository;

    @Autowired
    private UserTagsRepository userTagsRepository;

    @Autowired
    private UserExperienceRepository userExperienceRepository;

    @Autowired
    private UserAcademicsRepository userAcademicsRepository;

    @Autowired
    private NormalFollowRequestRepository normalFollowRequestRepository;

    @Autowired
    private UserDocumentsRepository userDocumentsRepository;

    @Override
    public void updateUser(User p) {
        userRepository.saveAndFlush(p);
    }

    @Override
    public void addProfilePic(UserProfilePic u_p) {
        try{
            userProfilePicRepository.saveAndFlush(u_p);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public UserProfilePic getUserPpByUsername(String username){

        return userProfilePicRepository.getUserProfilePicByusername(username);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void addUserTags(UserTags userTags1) {
        try{
            userTagsRepository.saveAndFlush(userTags1);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public UserTags getUserTagsByUsername(String username){
        return userTagsRepository.getUserTagsByUsername(username);
    }

    @Override
    public void addUserExperience(UserExperience userExperience1) {
        try{
            userExperienceRepository.saveAndFlush(userExperience1);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public List<UserExperience> getUserExperienceByUsername(String username){
        return userExperienceRepository.getUserExperienceByUsername(username);
    }

    @Override
    public List<UserAcademics> getUserAcademicsByUsername(String username){
        List<UserAcademics> userAcademics = userAcademicsRepository.getUserAcademicsByUsername(username);
        return userAcademics;
    }

    @Override
    public UserAcademics getUserAcademicsFromId(Long id){
        return userAcademicsRepository.findOne(id);
    }

    @Override
    public UserExperience getExperienceFromId(Long id) { return userExperienceRepository.findOne(id); }

    @Override
    public void addUserAcademics(UserAcademics userAcademics) {
        try{
            userAcademicsRepository.saveAndFlush(userAcademics);
        }
        catch(Exception e){
            System.out.println("error");
        }
    }

    @Override
    public void deleteThisAcademics(Long id){
        userAcademicsRepository.delete(id);
    }

    @Override
    public void deleteThisExperience(Long id){
        userExperienceRepository.delete(id);
    }

    @Override
    public List<NormalFollowRequest> checkFollowRequests(String username) {
        String sql = "SELECT * FROM normal_follow_request_table f" +
                " WHERE f.toProUsername = :username" +
                " AND f.status = 0";
        List<NormalFollowRequest> results = new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("username", username);

            results = query.getResultList();

        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        return results;

    }

    @Override
    public void approveFollowRequest(Long id) {
        String sql = "UPDATE normal_follow_request_table nfr" +
                " SET status = 1"+
                " WHERE id = :id";
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception "+e);
        }
    }

    @Override
    public void ignoreFollowRequest(Long id) {
        String sql = "UPDATE normal_follow_request_table nfr" +
                " SET status = 3"+
                " WHERE id = :id";
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception "+e);
        }
    }

    @Override
    public List<NormalFollowRequest> getIgnoredRequests() {

        String sql = "SELECT * FROM normal_follow_request_table f" +
                " WHERE f.status = 3";
        List<NormalFollowRequest> results = new ArrayList<>();
        try {
            Query query = em.createNativeQuery(sql);
            results = query.getResultList();
        }catch(Exception e){
            System.out.println("Exception "+e);
        }
        return results;

    }

    @Override
    public void saveUserDoc(UserDocuments userDocuments) {
        userDocumentsRepository.saveAndFlush(userDocuments);
    }

    @Override
    public List<UserDocInfo> checkForUploadedDocs(String username) {
        String sql = "SELECT * FROM user_documents_table ud" +
                " WHERE ud.username = :username";
        Query query = em.createNativeQuery(sql);
        query.setParameter("username", username);
        //List<UserDocuments> results = (List<UserDocuments>) query.getResultList();
        List<Object[]> results = query.getResultList();
        List<UserDocInfo> userDocInfos = new ArrayList<>();
        for(Object[] element : results) {

            UserDocInfo userDocInfo = new UserDocInfo();
            userDocInfo.setId(Long.parseLong(element[0].toString()));
            userDocInfo.setDocPath(element[1].toString());
            userDocInfos.add(userDocInfo);
        }
        return userDocInfos;
    }

    @Override
    public void deleteDocument(Long id) {
        userDocumentsRepository.delete(id);
    }
}
