package ae.pegasus.framework.verification.profiler;

import ae.pegasus.framework.model.*;
import org.apache.log4j.Logger;

import java.util.List;

import static org.junit.Assert.*;

abstract class ProfileMergeAttributeVerification {

    protected Logger log = Logger.getLogger(ProfileMergeAttributeVerification.class);

    // The next element in the chain of responsibility
    private ProfileMergeAttributeVerification next;

    ProfileMergeAttributeVerification setNext(ProfileMergeAttributeVerification verification) {
        next = verification;
        return verification;
    }

    public void verify(Profile profile1, Profile profile2, Profile mergedProfile) {

        verifyAttribute(profile1, profile2, mergedProfile);

        if (next != null) {
            next.verify(profile1, profile2, mergedProfile);
        }
    }

    abstract protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile);

    class ProfileMergeActiveUntilVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: ActiveUntil of merged profile");

            if (profile1.getActiveUntil() != null) {
                assertFalse(
                        mergedProfile.getActiveUntil().before(profile1.getActiveUntil())
                );
            } else if (profile2.getActiveUntil() != null) {
                assertFalse(
                        mergedProfile.getActiveUntil().before(profile2.getActiveUntil())
                );
            }
        }
    }

    class ProfileMergeActiveVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: isActive of merged profile");

            if (profile1.getActive().equals(TargetStatus.ACTIVE) || profile2.getActive().equals(TargetStatus.ACTIVE)) {
                assertEquals("At least one old merged target was ACTIVE, new target shouldBe ACTIVE too",
                        mergedProfile.getActive(), TargetStatus.ACTIVE);
            } else if (profile1.getActive().equals(TargetStatus.INACTIVE) || profile2.getActive().equals(TargetStatus.INACTIVE)) {
                assertEquals("At least one old merged target was INACTIVE, new target shouldBe INACTIVE too",
                        mergedProfile.getActive(), TargetStatus.INACTIVE);
            } else {
                assertEquals("At least one old merged target was ARCHIVED, new target shouldBe ARCHIVED too",
                        mergedProfile.getActive(), TargetStatus.ARCHIVED);
            }
        }
    }

    class ProfileMergeConsolidatedAttributesValidation extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: ConsolidatedAttributes of merged profile");

            //TODO update validation for consolidatedAttributes
            assertNotNull(mergedProfile.getConsolidatedAttributes());
        }
    }

    class ProfileMergeEntitiesVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: Entities of merged profile");

            List<SearchRecord> entities = profile1.getEntities();
            entities.addAll(profile2.getEntities());

            for (SearchRecord entity : entities) {
                assertTrue(mergedProfile.getEntities().contains(entity));
            }
        }
    }

    class ProfileMergeEntityCountVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: EntityCount of merged profile");

            // entityCount
            if (mergedProfile.getIdentifierCount() == null || mergedProfile.getIdentifierCount().equals(0)) {
                assertTrue(mergedProfile.getEntities().isEmpty());
            } else {
                assertEquals((int) mergedProfile.getIdentifierCount(), mergedProfile.getEntities().size());
            }
        }
    }

    class ProfileMergeJsonTypeVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: JsonType of merged profile");

            assertEquals(mergedProfile.getJsonType(), ProfilerJsonType.Profile);
        }
    }

    class ProfileMergeMergedIDsVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: MergedIDs of merged profile");

            assertTrue(mergedProfile.getMergingProfilesIDs().contains(profile1.getId()));
            assertTrue(mergedProfile.getMergingProfilesIDs().contains(profile2.getId()));
        }
    }

    class ProfileMergeNameVerification extends ProfileMergeAttributeVerification {

        @Override
        public void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: Name of merged profile");

            assertTrue("Merged target name should contain all names of original targets",
                    mergedProfile.getName().contains(profile1.getName()));
            assertTrue("Merged target name should contain all names of original targets",
                    mergedProfile.getName().contains(profile2.getName()));
        }
    }

    class ProfileMergeTargetCategoryVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: TargetCategory of merged profile");

            // target category
            if (profile1.getCategory().equals("Dangerous") || profile2.getCategory().equals("Dangerous")) {
                assertEquals(mergedProfile.getCategory(), ProfileCategory.Dangerous.getDisplayName());
            } else {
                assertEquals(mergedProfile.getCategory(), ProfileCategory.POI.getDisplayName());
            }
        }
    }

    class ProfileMergeTypeVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: Type of merged profile");

            // now it's only 'Individual' value
            assertEquals(mergedProfile.getType(), profile1.getType());
            assertEquals(mergedProfile.getType(), profile2.getType());
        }
    }

    class ProfileMergeAssignedTeamsVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: AssignedTeams of merged profile");

            assertTrue(mergedProfile.getAssignedTeams().containsAll(profile1.getAssignedTeams()));
            assertTrue(mergedProfile.getAssignedTeams().containsAll(profile2.getAssignedTeams()));
        }
    }

    class ProfileMergeDescriptionVerification extends ProfileMergeAttributeVerification {

        @Override
        protected void verifyAttribute(Profile profile1, Profile profile2, Profile mergedProfile) {
            log.info("Verification: Description of merged profile");

            assertTrue(mergedProfile.getProperties().getDescription().contains(profile1.getProperties().getDescription()));
            assertTrue(mergedProfile.getProperties().getDescription().contains(profile2.getProperties().getDescription()));
        }
    }

}