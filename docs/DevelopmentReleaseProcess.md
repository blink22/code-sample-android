# **Git conventions during project development:**

- Main branches develop, release/${releaseName}, master
- To merge PR at least one approval is needed
- Reviewing or Resolving PR comments should have a high priority 

---
## **Development Process:**

- Naming commits “[$clickUpTicketNumber] $description”-> example “[860qwvned] Add walkthrough page”
- Update Dev-notes.txt with changes done in this PR
- When new build is out for testing clear dev-notes.txt file
- Tickets branch names should be in the form  “$clickUpTicketNumber_$description” example: “860qwvned-12_Walkthrough-page”
- PR title should be in this form [$type][$clickUpTicketNumber] $Description
- Use rebase instead of merging whenever possible
- Resolving the conversations should be done by the reviewer
- PR should have at least one Approval to be merged

  - **For small tasks (changes don’t exceed 500 LOC)**
    - Checkout develop branch
    - Create branch for the ticket
    - Create PR for the branch
    - After the PR is approved 
      - If PR is only one task -> merge branch with squashing (result should be one code commit and one merge commit)
      - If PR contains multiple small fixes -> create commit for each fix and merge without squashing

  - **For big tasks**
    - Checkout develop branch
    - Create branch for parent ticket
    - Create sub-tasks on clickUp
    - For each sub-task create a branch from parent task branch
    - Create PR for each sub task targeting parent task branch (changes doesn’t exceed 500 LOC)
    - Squash commits for each sub task PR Before merging
    - After all sub tasks are merged
      - If there are no conflicts with develop -> merge directly to develop without squashing
      - If there are conflicts Ideally would be to rebase on develop but if there are many commits it can be considered to merge develop branch into feature branch and then merge c 
    - Delete Source branches for tasks once it’s merged on the master branches (develop,release,master)

---

## **Hot fixes Process:**

- Create branch from master branch with name “release/$releaseName”
- Apply fix
- Update version code & name
- Commits should be in the form [Hot-fix] $Description
- Generate signed .aab file & extract .apk from it (or directly upload .aab file to firebase)
- Once .apk is approved from QA upload build on the store
- Create a tag/release from the commit that the build was generated from
- Tag should be in the form vx.y.z for example v2.0.3
- Add in the release notes the Clickup ticket numbers that were fixed in this release
- Merge release branch to master (without deleting source branch nor squashing)
- Merge master branch to develop (without deleting source branch nor squashing)

---

## **Release Process:**

- Create Branch from develop branch with name “release/$releaseName”
- Update version code & name & clear dev-notes with desired version
- Commits should be in the form [Release] $Description
- Generate signed .aab file & upload .aab file on firebase app distribution
- Once .aab is approved from QA upload build on the store
  - Upload .aab approved file  
  - Update release notes (Confirm with PM on the content)
  - Start rollout to small percentage of users 5% for 1/2 days to make sure no major crashes reported
  - Increase rollout gradually every 2/3 days (20%, 70%, 100%) as long as there are no major crashes reported
  - If there are major crashes reported then halt the release and start the process of hot fixes
- Create a tag/release from the commit that the build was generated from
- Tag should be in the form vx.y.z for example v2.0.3
- Add in the release notes the Clickup ticket numbers that were fixed in this release
- Merge release branch to master (without deleting source branch nor squashing)
- Merge master branch to develop (without deleting source branch nor squashing)

---
