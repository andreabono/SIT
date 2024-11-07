//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.2 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.09.05 alle 11:56:25 AM CEST 
//


package org.quakeml.xmlns.bed._1;

import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.quakeml.xmlns.bed._1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EventParameters_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "eventParameters");
    private final static QName _EventDescription_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "description");
    private final static QName _EventComment_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "comment");
    private final static QName _EventFocalMechanism_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "focalMechanism");
    private final static QName _EventAmplitude_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "amplitude");
    private final static QName _EventMagnitude_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "magnitude");
    private final static QName _EventStationMagnitude_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "stationMagnitude");
    private final static QName _EventOrigin_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "origin");
    private final static QName _EventPick_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "pick");
    private final static QName _EventPreferredOriginID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "preferredOriginID");
    private final static QName _EventPreferredMagnitudeID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "preferredMagnitudeID");
    private final static QName _EventPreferredFocalMechanismID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "preferredFocalMechanismID");
    private final static QName _EventType_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "type");
    private final static QName _EventTypeCertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "typeCertainty");
    private final static QName _EventCreationInfo_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "creationInfo");
    private final static QName _PickTime_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "time");
    private final static QName _PickWaveformID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "waveformID");
    private final static QName _PickFilterID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "filterID");
    private final static QName _PickMethodID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "methodID");
    private final static QName _PickHorizontalSlowness_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "horizontalSlowness");
    private final static QName _PickBackazimuth_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "backazimuth");
    private final static QName _PickSlownessMethodID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "slownessMethodID");
    private final static QName _PickOnset_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "onset");
    private final static QName _PickPhaseHint_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "phaseHint");
    private final static QName _PickPolarity_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "polarity");
    private final static QName _PickEvaluationMode_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "evaluationMode");
    private final static QName _PickEvaluationStatus_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "evaluationStatus");
    private final static QName _OriginCompositeTime_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "compositeTime");
    private final static QName _OriginOriginUncertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "originUncertainty");
    private final static QName _OriginArrival_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "arrival");
    private final static QName _OriginLongitude_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "longitude");
    private final static QName _OriginLatitude_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "latitude");
    private final static QName _OriginDepth_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "depth");
    private final static QName _OriginDepthType_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "depthType");
    private final static QName _OriginTimeFixed_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "timeFixed");
    private final static QName _OriginEpicenterFixed_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "epicenterFixed");
    private final static QName _OriginReferenceSystemID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "referenceSystemID");
    private final static QName _OriginEarthModelID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "earthModelID");
    private final static QName _OriginQuality_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "quality");
    private final static QName _OriginRegion_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "region");
    private final static QName _ArrivalPickID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "pickID");
    private final static QName _ArrivalPhase_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "phase");
    private final static QName _ArrivalTimeCorrection_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "timeCorrection");
    private final static QName _ArrivalAzimuth_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "azimuth");
    private final static QName _ArrivalDistance_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "distance");
    private final static QName _ArrivalTakeoffAngle_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "takeoffAngle");
    private final static QName _ArrivalTimeResidual_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "timeResidual");
    private final static QName _ArrivalHorizontalSlownessResidual_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "horizontalSlownessResidual");
    private final static QName _ArrivalBackazimuthResidual_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "backazimuthResidual");
    private final static QName _ArrivalTimeWeight_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "timeWeight");
    private final static QName _ArrivalHorizontalSlownessWeight_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "horizontalSlownessWeight");
    private final static QName _ArrivalBackazimuthWeight_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "backazimuthWeight");
    private final static QName _OriginUncertaintyHorizontalUncertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "horizontalUncertainty");
    private final static QName _OriginUncertaintyMinHorizontalUncertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "minHorizontalUncertainty");
    private final static QName _OriginUncertaintyMaxHorizontalUncertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "maxHorizontalUncertainty");
    private final static QName _OriginUncertaintyAzimuthMaxHorizontalUncertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "azimuthMaxHorizontalUncertainty");
    private final static QName _OriginUncertaintyConfidenceEllipsoid_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "confidenceEllipsoid");
    private final static QName _OriginUncertaintyPreferredDescription_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "preferredDescription");
    private final static QName _OriginUncertaintyConfidenceLevel_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "confidenceLevel");
    private final static QName _StationMagnitudeOriginID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "originID");
    private final static QName _StationMagnitudeMag_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "mag");
    private final static QName _StationMagnitudeAmplitudeID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "amplitudeID");
    private final static QName _MagnitudeStationMagnitudeContribution_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "stationMagnitudeContribution");
    private final static QName _MagnitudeStationCount_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "stationCount");
    private final static QName _MagnitudeAzimuthalGap_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "azimuthalGap");
    private final static QName _StationMagnitudeContributionStationMagnitudeID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "stationMagnitudeID");
    private final static QName _StationMagnitudeContributionResidual_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "residual");
    private final static QName _StationMagnitudeContributionWeight_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "weight");
    private final static QName _AmplitudeGenericAmplitude_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "genericAmplitude");
    private final static QName _AmplitudeCategory_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "category");
    private final static QName _AmplitudeUnit_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "unit");
    private final static QName _AmplitudePeriod_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "period");
    private final static QName _AmplitudeSnr_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "snr");
    private final static QName _AmplitudeTimeWindow_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "timeWindow");
    private final static QName _AmplitudeScalingTime_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "scalingTime");
    private final static QName _AmplitudeMagnitudeHint_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "magnitudeHint");
    private final static QName _FocalMechanismMomentTensor_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "momentTensor");
    private final static QName _FocalMechanismTriggeringOriginID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "triggeringOriginID");
    private final static QName _FocalMechanismNodalPlanes_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "nodalPlanes");
    private final static QName _FocalMechanismPrincipalAxes_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "principalAxes");
    private final static QName _FocalMechanismStationPolarityCount_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "stationPolarityCount");
    private final static QName _FocalMechanismMisfit_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "misfit");
    private final static QName _FocalMechanismStationDistributionRatio_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "stationDistributionRatio");
    private final static QName _MomentTensorDataUsed_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "dataUsed");
    private final static QName _MomentTensorDerivedOriginID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "derivedOriginID");
    private final static QName _MomentTensorMomentMagnitudeID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "momentMagnitudeID");
    private final static QName _MomentTensorScalarMoment_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "scalarMoment");
    private final static QName _MomentTensorTensor_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "tensor");
    private final static QName _MomentTensorVariance_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "variance");
    private final static QName _MomentTensorVarianceReduction_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "varianceReduction");
    private final static QName _MomentTensorDoubleCouple_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "doubleCouple");
    private final static QName _MomentTensorClvd_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "clvd");
    private final static QName _MomentTensorIso_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "iso");
    private final static QName _MomentTensorGreensFunctionID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "greensFunctionID");
    private final static QName _MomentTensorSourceTimeFunction_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "sourceTimeFunction");
    private final static QName _MomentTensorInversionType_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "inversionType");
    private final static QName _ConfidenceEllipsoidSemiMajorAxisLength_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "semiMajorAxisLength");
    private final static QName _ConfidenceEllipsoidSemiMinorAxisLength_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "semiMinorAxisLength");
    private final static QName _ConfidenceEllipsoidSemiIntermediateAxisLength_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "semiIntermediateAxisLength");
    private final static QName _ConfidenceEllipsoidMajorAxisPlunge_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "majorAxisPlunge");
    private final static QName _ConfidenceEllipsoidMajorAxisAzimuth_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "majorAxisAzimuth");
    private final static QName _ConfidenceEllipsoidMajorAxisRotation_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "majorAxisRotation");
    private final static QName _NodalPlanesNodalPlane1_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "nodalPlane1");
    private final static QName _NodalPlanesNodalPlane2_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "nodalPlane2");
    private final static QName _SourceTimeFunctionDuration_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "duration");
    private final static QName _SourceTimeFunctionRiseTime_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "riseTime");
    private final static QName _SourceTimeFunctionDecayTime_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "decayTime");
    private final static QName _IntegerQuantityValue_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "value");
    private final static QName _IntegerQuantityUncertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "uncertainty");
    private final static QName _IntegerQuantityLowerUncertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "lowerUncertainty");
    private final static QName _IntegerQuantityUpperUncertainty_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "upperUncertainty");
    private final static QName _TimeWindowBegin_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "begin");
    private final static QName _TimeWindowEnd_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "end");
    private final static QName _TimeWindowReference_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "reference");
    private final static QName _NodalPlaneStrike_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "strike");
    private final static QName _NodalPlaneDip_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "dip");
    private final static QName _NodalPlaneRake_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "rake");
    private final static QName _OriginQualityAssociatedPhaseCount_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "associatedPhaseCount");
    private final static QName _OriginQualityUsedPhaseCount_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "usedPhaseCount");
    private final static QName _OriginQualityAssociatedStationCount_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "associatedStationCount");
    private final static QName _OriginQualityUsedStationCount_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "usedStationCount");
    private final static QName _OriginQualityDepthPhaseCount_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "depthPhaseCount");
    private final static QName _OriginQualityStandardError_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "standardError");
    private final static QName _OriginQualitySecondaryAzimuthalGap_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "secondaryAzimuthalGap");
    private final static QName _OriginQualityGroundTruthLevel_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "groundTruthLevel");
    private final static QName _OriginQualityMaximumDistance_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "maximumDistance");
    private final static QName _OriginQualityMinimumDistance_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "minimumDistance");
    private final static QName _OriginQualityMedianDistance_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "medianDistance");
    private final static QName _TensorMrr_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "Mrr");
    private final static QName _TensorMtt_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "Mtt");
    private final static QName _TensorMpp_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "Mpp");
    private final static QName _TensorMrt_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "Mrt");
    private final static QName _TensorMrp_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "Mrp");
    private final static QName _TensorMtp_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "Mtp");
    private final static QName _CompositeTimeYear_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "year");
    private final static QName _CompositeTimeMonth_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "month");
    private final static QName _CompositeTimeDay_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "day");
    private final static QName _CompositeTimeHour_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "hour");
    private final static QName _CompositeTimeMinute_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "minute");
    private final static QName _CompositeTimeSecond_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "second");
    private final static QName _DataUsedWaveType_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "waveType");
    private final static QName _DataUsedComponentCount_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "componentCount");
    private final static QName _DataUsedShortestPeriod_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "shortestPeriod");
    private final static QName _DataUsedLongestPeriod_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "longestPeriod");
    private final static QName _PrincipalAxesTAxis_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "tAxis");
    private final static QName _PrincipalAxesPAxis_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "pAxis");
    private final static QName _PrincipalAxesNAxis_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "nAxis");
    private final static QName _AxisPlunge_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "plunge");
    private final static QName _AxisLength_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "length");
    private final static QName _CreationInfoAgencyID_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "agencyID");
    private final static QName _CreationInfoAgencyURI_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "agencyURI");
    private final static QName _CreationInfoAuthor_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "author");
    private final static QName _CreationInfoAuthorURI_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "authorURI");
    private final static QName _CreationInfoCreationTime_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "creationTime");
    private final static QName _CreationInfoVersion_QNAME = new QName("http://quakeml.org/xmlns/bed/1.2", "version");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.quakeml.xmlns.bed._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EventParameters }
     * 
     */
    public EventParameters createEventParameters() {
        return new EventParameters();
    }

    /**
     * Create an instance of {@link TimeQuantity }
     * 
     */
    public TimeQuantity createTimeQuantity() {
        return new TimeQuantity();
    }

    /**
     * Create an instance of {@link CreationInfo }
     * 
     */
    public CreationInfo createCreationInfo() {
        return new CreationInfo();
    }

    /**
     * Create an instance of {@link EventDescription }
     * 
     */
    public EventDescription createEventDescription() {
        return new EventDescription();
    }

    /**
     * Create an instance of {@link Phase }
     * 
     */
    public Phase createPhase() {
        return new Phase();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link Axis }
     * 
     */
    public Axis createAxis() {
        return new Axis();
    }

    /**
     * Create an instance of {@link PrincipalAxes }
     * 
     */
    public PrincipalAxes createPrincipalAxes() {
        return new PrincipalAxes();
    }

    /**
     * Create an instance of {@link DataUsed }
     * 
     */
    public DataUsed createDataUsed() {
        return new DataUsed();
    }

    /**
     * Create an instance of {@link CompositeTime }
     * 
     */
    public CompositeTime createCompositeTime() {
        return new CompositeTime();
    }

    /**
     * Create an instance of {@link Tensor }
     * 
     */
    public Tensor createTensor() {
        return new Tensor();
    }

    /**
     * Create an instance of {@link OriginQuality }
     * 
     */
    public OriginQuality createOriginQuality() {
        return new OriginQuality();
    }

    /**
     * Create an instance of {@link RealQuantity }
     * 
     */
    public RealQuantity createRealQuantity() {
        return new RealQuantity();
    }

    /**
     * Create an instance of {@link NodalPlane }
     * 
     */
    public NodalPlane createNodalPlane() {
        return new NodalPlane();
    }

    /**
     * Create an instance of {@link TimeWindow }
     * 
     */
    public TimeWindow createTimeWindow() {
        return new TimeWindow();
    }

    /**
     * Create an instance of {@link WaveformStreamID }
     * 
     */
    public WaveformStreamID createWaveformStreamID() {
        return new WaveformStreamID();
    }

    /**
     * Create an instance of {@link IntegerQuantity }
     * 
     */
    public IntegerQuantity createIntegerQuantity() {
        return new IntegerQuantity();
    }

    /**
     * Create an instance of {@link SourceTimeFunction }
     * 
     */
    public SourceTimeFunction createSourceTimeFunction() {
        return new SourceTimeFunction();
    }

    /**
     * Create an instance of {@link NodalPlanes }
     * 
     */
    public NodalPlanes createNodalPlanes() {
        return new NodalPlanes();
    }

    /**
     * Create an instance of {@link ConfidenceEllipsoid }
     * 
     */
    public ConfidenceEllipsoid createConfidenceEllipsoid() {
        return new ConfidenceEllipsoid();
    }

    /**
     * Create an instance of {@link MomentTensor }
     * 
     */
    public MomentTensor createMomentTensor() {
        return new MomentTensor();
    }

    /**
     * Create an instance of {@link FocalMechanism }
     * 
     */
    public FocalMechanism createFocalMechanism() {
        return new FocalMechanism();
    }

    /**
     * Create an instance of {@link Amplitude }
     * 
     */
    public Amplitude createAmplitude() {
        return new Amplitude();
    }

    /**
     * Create an instance of {@link StationMagnitudeContribution }
     * 
     */
    public StationMagnitudeContribution createStationMagnitudeContribution() {
        return new StationMagnitudeContribution();
    }

    /**
     * Create an instance of {@link Magnitude }
     * 
     */
    public Magnitude createMagnitude() {
        return new Magnitude();
    }

    /**
     * Create an instance of {@link StationMagnitude }
     * 
     */
    public StationMagnitude createStationMagnitude() {
        return new StationMagnitude();
    }

    /**
     * Create an instance of {@link OriginUncertainty }
     * 
     */
    public OriginUncertainty createOriginUncertainty() {
        return new OriginUncertainty();
    }

    /**
     * Create an instance of {@link Arrival }
     * 
     */
    public Arrival createArrival() {
        return new Arrival();
    }

    /**
     * Create an instance of {@link Origin }
     * 
     */
    public Origin createOrigin() {
        return new Origin();
    }

    /**
     * Create an instance of {@link Pick }
     * 
     */
    public Pick createPick() {
        return new Pick();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventParameters }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventParameters }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "eventParameters")
    public JAXBElement<EventParameters> createEventParameters(EventParameters value) {
        return new JAXBElement<EventParameters>(_EventParameters_QNAME, EventParameters.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventDescription }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventDescription }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "description", scope = Event.class)
    public JAXBElement<EventDescription> createEventDescription(EventDescription value) {
        return new JAXBElement<EventDescription>(_EventDescription_QNAME, EventDescription.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = Event.class)
    public JAXBElement<Comment> createEventComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FocalMechanism }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FocalMechanism }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "focalMechanism", scope = Event.class)
    public JAXBElement<FocalMechanism> createEventFocalMechanism(FocalMechanism value) {
        return new JAXBElement<FocalMechanism>(_EventFocalMechanism_QNAME, FocalMechanism.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Amplitude }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Amplitude }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "amplitude", scope = Event.class)
    public JAXBElement<Amplitude> createEventAmplitude(Amplitude value) {
        return new JAXBElement<Amplitude>(_EventAmplitude_QNAME, Amplitude.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Magnitude }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Magnitude }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "magnitude", scope = Event.class)
    public JAXBElement<Magnitude> createEventMagnitude(Magnitude value) {
        return new JAXBElement<Magnitude>(_EventMagnitude_QNAME, Magnitude.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StationMagnitude }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StationMagnitude }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "stationMagnitude", scope = Event.class)
    public JAXBElement<StationMagnitude> createEventStationMagnitude(StationMagnitude value) {
        return new JAXBElement<StationMagnitude>(_EventStationMagnitude_QNAME, StationMagnitude.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Origin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Origin }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "origin", scope = Event.class)
    public JAXBElement<Origin> createEventOrigin(Origin value) {
        return new JAXBElement<Origin>(_EventOrigin_QNAME, Origin.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pick }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Pick }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "pick", scope = Event.class)
    public JAXBElement<Pick> createEventPick(Pick value) {
        return new JAXBElement<Pick>(_EventPick_QNAME, Pick.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "preferredOriginID", scope = Event.class)
    public JAXBElement<String> createEventPreferredOriginID(String value) {
        return new JAXBElement<String>(_EventPreferredOriginID_QNAME, String.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "preferredMagnitudeID", scope = Event.class)
    public JAXBElement<String> createEventPreferredMagnitudeID(String value) {
        return new JAXBElement<String>(_EventPreferredMagnitudeID_QNAME, String.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "preferredFocalMechanismID", scope = Event.class)
    public JAXBElement<String> createEventPreferredFocalMechanismID(String value) {
        return new JAXBElement<String>(_EventPreferredFocalMechanismID_QNAME, String.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventType }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "type", scope = Event.class)
    public JAXBElement<EventType> createEventType(EventType value) {
        return new JAXBElement<EventType>(_EventType_QNAME, EventType.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventTypeCertainty }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventTypeCertainty }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "typeCertainty", scope = Event.class)
    public JAXBElement<EventTypeCertainty> createEventTypeCertainty(EventTypeCertainty value) {
        return new JAXBElement<EventTypeCertainty>(_EventTypeCertainty_QNAME, EventTypeCertainty.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = Event.class)
    public JAXBElement<CreationInfo> createEventCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, Event.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = Pick.class)
    public JAXBElement<Comment> createPickComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "time", scope = Pick.class)
    public JAXBElement<TimeQuantity> createPickTime(TimeQuantity value) {
        return new JAXBElement<TimeQuantity>(_PickTime_QNAME, TimeQuantity.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "waveformID", scope = Pick.class)
    public JAXBElement<WaveformStreamID> createPickWaveformID(WaveformStreamID value) {
        return new JAXBElement<WaveformStreamID>(_PickWaveformID_QNAME, WaveformStreamID.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "filterID", scope = Pick.class)
    public JAXBElement<String> createPickFilterID(String value) {
        return new JAXBElement<String>(_PickFilterID_QNAME, String.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "methodID", scope = Pick.class)
    public JAXBElement<String> createPickMethodID(String value) {
        return new JAXBElement<String>(_PickMethodID_QNAME, String.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "horizontalSlowness", scope = Pick.class)
    public JAXBElement<RealQuantity> createPickHorizontalSlowness(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_PickHorizontalSlowness_QNAME, RealQuantity.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "backazimuth", scope = Pick.class)
    public JAXBElement<RealQuantity> createPickBackazimuth(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_PickBackazimuth_QNAME, RealQuantity.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "slownessMethodID", scope = Pick.class)
    public JAXBElement<String> createPickSlownessMethodID(String value) {
        return new JAXBElement<String>(_PickSlownessMethodID_QNAME, String.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PickOnset }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PickOnset }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "onset", scope = Pick.class)
    public JAXBElement<PickOnset> createPickOnset(PickOnset value) {
        return new JAXBElement<PickOnset>(_PickOnset_QNAME, PickOnset.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Phase }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Phase }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "phaseHint", scope = Pick.class)
    public JAXBElement<Phase> createPickPhaseHint(Phase value) {
        return new JAXBElement<Phase>(_PickPhaseHint_QNAME, Phase.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PickPolarity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PickPolarity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "polarity", scope = Pick.class)
    public JAXBElement<PickPolarity> createPickPolarity(PickPolarity value) {
        return new JAXBElement<PickPolarity>(_PickPolarity_QNAME, PickPolarity.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationMode", scope = Pick.class)
    public JAXBElement<EvaluationMode> createPickEvaluationMode(EvaluationMode value) {
        return new JAXBElement<EvaluationMode>(_PickEvaluationMode_QNAME, EvaluationMode.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationStatus", scope = Pick.class)
    public JAXBElement<EvaluationStatus> createPickEvaluationStatus(EvaluationStatus value) {
        return new JAXBElement<EvaluationStatus>(_PickEvaluationStatus_QNAME, EvaluationStatus.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = Pick.class)
    public JAXBElement<CreationInfo> createPickCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, Pick.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompositeTime }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CompositeTime }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "compositeTime", scope = Origin.class)
    public JAXBElement<CompositeTime> createOriginCompositeTime(CompositeTime value) {
        return new JAXBElement<CompositeTime>(_OriginCompositeTime_QNAME, CompositeTime.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = Origin.class)
    public JAXBElement<Comment> createOriginComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OriginUncertainty }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OriginUncertainty }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "originUncertainty", scope = Origin.class)
    public JAXBElement<OriginUncertainty> createOriginOriginUncertainty(OriginUncertainty value) {
        return new JAXBElement<OriginUncertainty>(_OriginOriginUncertainty_QNAME, OriginUncertainty.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Arrival }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Arrival }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "arrival", scope = Origin.class)
    public JAXBElement<Arrival> createOriginArrival(Arrival value) {
        return new JAXBElement<Arrival>(_OriginArrival_QNAME, Arrival.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "time", scope = Origin.class)
    public JAXBElement<TimeQuantity> createOriginTime(TimeQuantity value) {
        return new JAXBElement<TimeQuantity>(_PickTime_QNAME, TimeQuantity.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "longitude", scope = Origin.class)
    public JAXBElement<RealQuantity> createOriginLongitude(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_OriginLongitude_QNAME, RealQuantity.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "latitude", scope = Origin.class)
    public JAXBElement<RealQuantity> createOriginLatitude(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_OriginLatitude_QNAME, RealQuantity.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "depth", scope = Origin.class)
    public JAXBElement<RealQuantity> createOriginDepth(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_OriginDepth_QNAME, RealQuantity.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OriginDepthType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OriginDepthType }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "depthType", scope = Origin.class)
    public JAXBElement<OriginDepthType> createOriginDepthType(OriginDepthType value) {
        return new JAXBElement<OriginDepthType>(_OriginDepthType_QNAME, OriginDepthType.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "timeFixed", scope = Origin.class)
    public JAXBElement<Boolean> createOriginTimeFixed(Boolean value) {
        return new JAXBElement<Boolean>(_OriginTimeFixed_QNAME, Boolean.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "epicenterFixed", scope = Origin.class)
    public JAXBElement<Boolean> createOriginEpicenterFixed(Boolean value) {
        return new JAXBElement<Boolean>(_OriginEpicenterFixed_QNAME, Boolean.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "referenceSystemID", scope = Origin.class)
    public JAXBElement<String> createOriginReferenceSystemID(String value) {
        return new JAXBElement<String>(_OriginReferenceSystemID_QNAME, String.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "methodID", scope = Origin.class)
    public JAXBElement<String> createOriginMethodID(String value) {
        return new JAXBElement<String>(_PickMethodID_QNAME, String.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "earthModelID", scope = Origin.class)
    public JAXBElement<String> createOriginEarthModelID(String value) {
        return new JAXBElement<String>(_OriginEarthModelID_QNAME, String.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OriginQuality }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OriginQuality }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "quality", scope = Origin.class)
    public JAXBElement<OriginQuality> createOriginQuality(OriginQuality value) {
        return new JAXBElement<OriginQuality>(_OriginQuality_QNAME, OriginQuality.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OriginType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OriginType }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "type", scope = Origin.class)
    public JAXBElement<OriginType> createOriginType(OriginType value) {
        return new JAXBElement<OriginType>(_EventType_QNAME, OriginType.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "region", scope = Origin.class)
    public JAXBElement<String> createOriginRegion(String value) {
        return new JAXBElement<String>(_OriginRegion_QNAME, String.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationMode", scope = Origin.class)
    public JAXBElement<EvaluationMode> createOriginEvaluationMode(EvaluationMode value) {
        return new JAXBElement<EvaluationMode>(_PickEvaluationMode_QNAME, EvaluationMode.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationStatus", scope = Origin.class)
    public JAXBElement<EvaluationStatus> createOriginEvaluationStatus(EvaluationStatus value) {
        return new JAXBElement<EvaluationStatus>(_PickEvaluationStatus_QNAME, EvaluationStatus.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = Origin.class)
    public JAXBElement<CreationInfo> createOriginCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, Origin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = Arrival.class)
    public JAXBElement<Comment> createArrivalComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "pickID", scope = Arrival.class)
    public JAXBElement<String> createArrivalPickID(String value) {
        return new JAXBElement<String>(_ArrivalPickID_QNAME, String.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Phase }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Phase }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "phase", scope = Arrival.class)
    public JAXBElement<Phase> createArrivalPhase(Phase value) {
        return new JAXBElement<Phase>(_ArrivalPhase_QNAME, Phase.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "timeCorrection", scope = Arrival.class)
    public JAXBElement<Double> createArrivalTimeCorrection(Double value) {
        return new JAXBElement<Double>(_ArrivalTimeCorrection_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "azimuth", scope = Arrival.class)
    public JAXBElement<Double> createArrivalAzimuth(Double value) {
        return new JAXBElement<Double>(_ArrivalAzimuth_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "distance", scope = Arrival.class)
    public JAXBElement<Double> createArrivalDistance(Double value) {
        return new JAXBElement<Double>(_ArrivalDistance_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "takeoffAngle", scope = Arrival.class)
    public JAXBElement<RealQuantity> createArrivalTakeoffAngle(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_ArrivalTakeoffAngle_QNAME, RealQuantity.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "timeResidual", scope = Arrival.class)
    public JAXBElement<Double> createArrivalTimeResidual(Double value) {
        return new JAXBElement<Double>(_ArrivalTimeResidual_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "horizontalSlownessResidual", scope = Arrival.class)
    public JAXBElement<Double> createArrivalHorizontalSlownessResidual(Double value) {
        return new JAXBElement<Double>(_ArrivalHorizontalSlownessResidual_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "backazimuthResidual", scope = Arrival.class)
    public JAXBElement<Double> createArrivalBackazimuthResidual(Double value) {
        return new JAXBElement<Double>(_ArrivalBackazimuthResidual_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "timeWeight", scope = Arrival.class)
    public JAXBElement<Double> createArrivalTimeWeight(Double value) {
        return new JAXBElement<Double>(_ArrivalTimeWeight_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "horizontalSlownessWeight", scope = Arrival.class)
    public JAXBElement<Double> createArrivalHorizontalSlownessWeight(Double value) {
        return new JAXBElement<Double>(_ArrivalHorizontalSlownessWeight_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "backazimuthWeight", scope = Arrival.class)
    public JAXBElement<Double> createArrivalBackazimuthWeight(Double value) {
        return new JAXBElement<Double>(_ArrivalBackazimuthWeight_QNAME, Double.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "earthModelID", scope = Arrival.class)
    public JAXBElement<String> createArrivalEarthModelID(String value) {
        return new JAXBElement<String>(_OriginEarthModelID_QNAME, String.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = Arrival.class)
    public JAXBElement<CreationInfo> createArrivalCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, Arrival.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "horizontalUncertainty", scope = OriginUncertainty.class)
    public JAXBElement<Double> createOriginUncertaintyHorizontalUncertainty(Double value) {
        return new JAXBElement<Double>(_OriginUncertaintyHorizontalUncertainty_QNAME, Double.class, OriginUncertainty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "minHorizontalUncertainty", scope = OriginUncertainty.class)
    public JAXBElement<Double> createOriginUncertaintyMinHorizontalUncertainty(Double value) {
        return new JAXBElement<Double>(_OriginUncertaintyMinHorizontalUncertainty_QNAME, Double.class, OriginUncertainty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "maxHorizontalUncertainty", scope = OriginUncertainty.class)
    public JAXBElement<Double> createOriginUncertaintyMaxHorizontalUncertainty(Double value) {
        return new JAXBElement<Double>(_OriginUncertaintyMaxHorizontalUncertainty_QNAME, Double.class, OriginUncertainty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "azimuthMaxHorizontalUncertainty", scope = OriginUncertainty.class)
    public JAXBElement<Double> createOriginUncertaintyAzimuthMaxHorizontalUncertainty(Double value) {
        return new JAXBElement<Double>(_OriginUncertaintyAzimuthMaxHorizontalUncertainty_QNAME, Double.class, OriginUncertainty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfidenceEllipsoid }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConfidenceEllipsoid }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "confidenceEllipsoid", scope = OriginUncertainty.class)
    public JAXBElement<ConfidenceEllipsoid> createOriginUncertaintyConfidenceEllipsoid(ConfidenceEllipsoid value) {
        return new JAXBElement<ConfidenceEllipsoid>(_OriginUncertaintyConfidenceEllipsoid_QNAME, ConfidenceEllipsoid.class, OriginUncertainty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OriginUncertaintyDescription }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OriginUncertaintyDescription }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "preferredDescription", scope = OriginUncertainty.class)
    public JAXBElement<OriginUncertaintyDescription> createOriginUncertaintyPreferredDescription(OriginUncertaintyDescription value) {
        return new JAXBElement<OriginUncertaintyDescription>(_OriginUncertaintyPreferredDescription_QNAME, OriginUncertaintyDescription.class, OriginUncertainty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "confidenceLevel", scope = OriginUncertainty.class)
    public JAXBElement<Double> createOriginUncertaintyConfidenceLevel(Double value) {
        return new JAXBElement<Double>(_OriginUncertaintyConfidenceLevel_QNAME, Double.class, OriginUncertainty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = StationMagnitude.class)
    public JAXBElement<Comment> createStationMagnitudeComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, StationMagnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "originID", scope = StationMagnitude.class)
    public JAXBElement<String> createStationMagnitudeOriginID(String value) {
        return new JAXBElement<String>(_StationMagnitudeOriginID_QNAME, String.class, StationMagnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "mag", scope = StationMagnitude.class)
    public JAXBElement<RealQuantity> createStationMagnitudeMag(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_StationMagnitudeMag_QNAME, RealQuantity.class, StationMagnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "type", scope = StationMagnitude.class)
    public JAXBElement<String> createStationMagnitudeType(String value) {
        return new JAXBElement<String>(_EventType_QNAME, String.class, StationMagnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "amplitudeID", scope = StationMagnitude.class)
    public JAXBElement<String> createStationMagnitudeAmplitudeID(String value) {
        return new JAXBElement<String>(_StationMagnitudeAmplitudeID_QNAME, String.class, StationMagnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "methodID", scope = StationMagnitude.class)
    public JAXBElement<String> createStationMagnitudeMethodID(String value) {
        return new JAXBElement<String>(_PickMethodID_QNAME, String.class, StationMagnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "waveformID", scope = StationMagnitude.class)
    public JAXBElement<WaveformStreamID> createStationMagnitudeWaveformID(WaveformStreamID value) {
        return new JAXBElement<WaveformStreamID>(_PickWaveformID_QNAME, WaveformStreamID.class, StationMagnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = StationMagnitude.class)
    public JAXBElement<CreationInfo> createStationMagnitudeCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, StationMagnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = Magnitude.class)
    public JAXBElement<Comment> createMagnitudeComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StationMagnitudeContribution }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StationMagnitudeContribution }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "stationMagnitudeContribution", scope = Magnitude.class)
    public JAXBElement<StationMagnitudeContribution> createMagnitudeStationMagnitudeContribution(StationMagnitudeContribution value) {
        return new JAXBElement<StationMagnitudeContribution>(_MagnitudeStationMagnitudeContribution_QNAME, StationMagnitudeContribution.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "mag", scope = Magnitude.class)
    public JAXBElement<RealQuantity> createMagnitudeMag(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_StationMagnitudeMag_QNAME, RealQuantity.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "type", scope = Magnitude.class)
    public JAXBElement<String> createMagnitudeType(String value) {
        return new JAXBElement<String>(_EventType_QNAME, String.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "originID", scope = Magnitude.class)
    public JAXBElement<String> createMagnitudeOriginID(String value) {
        return new JAXBElement<String>(_StationMagnitudeOriginID_QNAME, String.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "methodID", scope = Magnitude.class)
    public JAXBElement<String> createMagnitudeMethodID(String value) {
        return new JAXBElement<String>(_PickMethodID_QNAME, String.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "stationCount", scope = Magnitude.class)
    public JAXBElement<BigInteger> createMagnitudeStationCount(BigInteger value) {
        return new JAXBElement<BigInteger>(_MagnitudeStationCount_QNAME, BigInteger.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "azimuthalGap", scope = Magnitude.class)
    public JAXBElement<Double> createMagnitudeAzimuthalGap(Double value) {
        return new JAXBElement<Double>(_MagnitudeAzimuthalGap_QNAME, Double.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationMode", scope = Magnitude.class)
    public JAXBElement<EvaluationMode> createMagnitudeEvaluationMode(EvaluationMode value) {
        return new JAXBElement<EvaluationMode>(_PickEvaluationMode_QNAME, EvaluationMode.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationStatus", scope = Magnitude.class)
    public JAXBElement<EvaluationStatus> createMagnitudeEvaluationStatus(EvaluationStatus value) {
        return new JAXBElement<EvaluationStatus>(_PickEvaluationStatus_QNAME, EvaluationStatus.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = Magnitude.class)
    public JAXBElement<CreationInfo> createMagnitudeCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, Magnitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "stationMagnitudeID", scope = StationMagnitudeContribution.class)
    public JAXBElement<String> createStationMagnitudeContributionStationMagnitudeID(String value) {
        return new JAXBElement<String>(_StationMagnitudeContributionStationMagnitudeID_QNAME, String.class, StationMagnitudeContribution.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "residual", scope = StationMagnitudeContribution.class)
    public JAXBElement<Double> createStationMagnitudeContributionResidual(Double value) {
        return new JAXBElement<Double>(_StationMagnitudeContributionResidual_QNAME, Double.class, StationMagnitudeContribution.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "weight", scope = StationMagnitudeContribution.class)
    public JAXBElement<Double> createStationMagnitudeContributionWeight(Double value) {
        return new JAXBElement<Double>(_StationMagnitudeContributionWeight_QNAME, Double.class, StationMagnitudeContribution.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = Amplitude.class)
    public JAXBElement<Comment> createAmplitudeComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "genericAmplitude", scope = Amplitude.class)
    public JAXBElement<RealQuantity> createAmplitudeGenericAmplitude(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_AmplitudeGenericAmplitude_QNAME, RealQuantity.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "type", scope = Amplitude.class)
    public JAXBElement<String> createAmplitudeType(String value) {
        return new JAXBElement<String>(_EventType_QNAME, String.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AmplitudeCategory }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AmplitudeCategory }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "category", scope = Amplitude.class)
    public JAXBElement<AmplitudeCategory> createAmplitudeCategory(AmplitudeCategory value) {
        return new JAXBElement<AmplitudeCategory>(_AmplitudeCategory_QNAME, AmplitudeCategory.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "unit", scope = Amplitude.class)
    public JAXBElement<String> createAmplitudeUnit(String value) {
        return new JAXBElement<String>(_AmplitudeUnit_QNAME, String.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "methodID", scope = Amplitude.class)
    public JAXBElement<String> createAmplitudeMethodID(String value) {
        return new JAXBElement<String>(_PickMethodID_QNAME, String.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "period", scope = Amplitude.class)
    public JAXBElement<RealQuantity> createAmplitudePeriod(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_AmplitudePeriod_QNAME, RealQuantity.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "snr", scope = Amplitude.class)
    public JAXBElement<Double> createAmplitudeSnr(Double value) {
        return new JAXBElement<Double>(_AmplitudeSnr_QNAME, Double.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeWindow }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeWindow }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "timeWindow", scope = Amplitude.class)
    public JAXBElement<TimeWindow> createAmplitudeTimeWindow(TimeWindow value) {
        return new JAXBElement<TimeWindow>(_AmplitudeTimeWindow_QNAME, TimeWindow.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "pickID", scope = Amplitude.class)
    public JAXBElement<String> createAmplitudePickID(String value) {
        return new JAXBElement<String>(_ArrivalPickID_QNAME, String.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "waveformID", scope = Amplitude.class)
    public JAXBElement<WaveformStreamID> createAmplitudeWaveformID(WaveformStreamID value) {
        return new JAXBElement<WaveformStreamID>(_PickWaveformID_QNAME, WaveformStreamID.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "filterID", scope = Amplitude.class)
    public JAXBElement<String> createAmplitudeFilterID(String value) {
        return new JAXBElement<String>(_PickFilterID_QNAME, String.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "scalingTime", scope = Amplitude.class)
    public JAXBElement<TimeQuantity> createAmplitudeScalingTime(TimeQuantity value) {
        return new JAXBElement<TimeQuantity>(_AmplitudeScalingTime_QNAME, TimeQuantity.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "magnitudeHint", scope = Amplitude.class)
    public JAXBElement<String> createAmplitudeMagnitudeHint(String value) {
        return new JAXBElement<String>(_AmplitudeMagnitudeHint_QNAME, String.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationMode", scope = Amplitude.class)
    public JAXBElement<EvaluationMode> createAmplitudeEvaluationMode(EvaluationMode value) {
        return new JAXBElement<EvaluationMode>(_PickEvaluationMode_QNAME, EvaluationMode.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationStatus", scope = Amplitude.class)
    public JAXBElement<EvaluationStatus> createAmplitudeEvaluationStatus(EvaluationStatus value) {
        return new JAXBElement<EvaluationStatus>(_PickEvaluationStatus_QNAME, EvaluationStatus.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = Amplitude.class)
    public JAXBElement<CreationInfo> createAmplitudeCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, Amplitude.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "waveformID", scope = FocalMechanism.class)
    public JAXBElement<WaveformStreamID> createFocalMechanismWaveformID(WaveformStreamID value) {
        return new JAXBElement<WaveformStreamID>(_PickWaveformID_QNAME, WaveformStreamID.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = FocalMechanism.class)
    public JAXBElement<Comment> createFocalMechanismComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MomentTensor }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MomentTensor }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "momentTensor", scope = FocalMechanism.class)
    public JAXBElement<MomentTensor> createFocalMechanismMomentTensor(MomentTensor value) {
        return new JAXBElement<MomentTensor>(_FocalMechanismMomentTensor_QNAME, MomentTensor.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "triggeringOriginID", scope = FocalMechanism.class)
    public JAXBElement<String> createFocalMechanismTriggeringOriginID(String value) {
        return new JAXBElement<String>(_FocalMechanismTriggeringOriginID_QNAME, String.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodalPlanes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NodalPlanes }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "nodalPlanes", scope = FocalMechanism.class)
    public JAXBElement<NodalPlanes> createFocalMechanismNodalPlanes(NodalPlanes value) {
        return new JAXBElement<NodalPlanes>(_FocalMechanismNodalPlanes_QNAME, NodalPlanes.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrincipalAxes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PrincipalAxes }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "principalAxes", scope = FocalMechanism.class)
    public JAXBElement<PrincipalAxes> createFocalMechanismPrincipalAxes(PrincipalAxes value) {
        return new JAXBElement<PrincipalAxes>(_FocalMechanismPrincipalAxes_QNAME, PrincipalAxes.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "azimuthalGap", scope = FocalMechanism.class)
    public JAXBElement<Double> createFocalMechanismAzimuthalGap(Double value) {
        return new JAXBElement<Double>(_MagnitudeAzimuthalGap_QNAME, Double.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "stationPolarityCount", scope = FocalMechanism.class)
    public JAXBElement<Integer> createFocalMechanismStationPolarityCount(Integer value) {
        return new JAXBElement<Integer>(_FocalMechanismStationPolarityCount_QNAME, Integer.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "misfit", scope = FocalMechanism.class)
    public JAXBElement<Double> createFocalMechanismMisfit(Double value) {
        return new JAXBElement<Double>(_FocalMechanismMisfit_QNAME, Double.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "stationDistributionRatio", scope = FocalMechanism.class)
    public JAXBElement<Double> createFocalMechanismStationDistributionRatio(Double value) {
        return new JAXBElement<Double>(_FocalMechanismStationDistributionRatio_QNAME, Double.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "methodID", scope = FocalMechanism.class)
    public JAXBElement<String> createFocalMechanismMethodID(String value) {
        return new JAXBElement<String>(_PickMethodID_QNAME, String.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationMode", scope = FocalMechanism.class)
    public JAXBElement<EvaluationMode> createFocalMechanismEvaluationMode(EvaluationMode value) {
        return new JAXBElement<EvaluationMode>(_PickEvaluationMode_QNAME, EvaluationMode.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "evaluationStatus", scope = FocalMechanism.class)
    public JAXBElement<EvaluationStatus> createFocalMechanismEvaluationStatus(EvaluationStatus value) {
        return new JAXBElement<EvaluationStatus>(_PickEvaluationStatus_QNAME, EvaluationStatus.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = FocalMechanism.class)
    public JAXBElement<CreationInfo> createFocalMechanismCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, FocalMechanism.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataUsed }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataUsed }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "dataUsed", scope = MomentTensor.class)
    public JAXBElement<DataUsed> createMomentTensorDataUsed(DataUsed value) {
        return new JAXBElement<DataUsed>(_MomentTensorDataUsed_QNAME, DataUsed.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "comment", scope = MomentTensor.class)
    public JAXBElement<Comment> createMomentTensorComment(Comment value) {
        return new JAXBElement<Comment>(_EventComment_QNAME, Comment.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "derivedOriginID", scope = MomentTensor.class)
    public JAXBElement<String> createMomentTensorDerivedOriginID(String value) {
        return new JAXBElement<String>(_MomentTensorDerivedOriginID_QNAME, String.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "momentMagnitudeID", scope = MomentTensor.class)
    public JAXBElement<String> createMomentTensorMomentMagnitudeID(String value) {
        return new JAXBElement<String>(_MomentTensorMomentMagnitudeID_QNAME, String.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "scalarMoment", scope = MomentTensor.class)
    public JAXBElement<RealQuantity> createMomentTensorScalarMoment(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_MomentTensorScalarMoment_QNAME, RealQuantity.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tensor }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Tensor }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "tensor", scope = MomentTensor.class)
    public JAXBElement<Tensor> createMomentTensorTensor(Tensor value) {
        return new JAXBElement<Tensor>(_MomentTensorTensor_QNAME, Tensor.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "variance", scope = MomentTensor.class)
    public JAXBElement<Double> createMomentTensorVariance(Double value) {
        return new JAXBElement<Double>(_MomentTensorVariance_QNAME, Double.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "varianceReduction", scope = MomentTensor.class)
    public JAXBElement<Double> createMomentTensorVarianceReduction(Double value) {
        return new JAXBElement<Double>(_MomentTensorVarianceReduction_QNAME, Double.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "doubleCouple", scope = MomentTensor.class)
    public JAXBElement<Double> createMomentTensorDoubleCouple(Double value) {
        return new JAXBElement<Double>(_MomentTensorDoubleCouple_QNAME, Double.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "clvd", scope = MomentTensor.class)
    public JAXBElement<Double> createMomentTensorClvd(Double value) {
        return new JAXBElement<Double>(_MomentTensorClvd_QNAME, Double.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "iso", scope = MomentTensor.class)
    public JAXBElement<Double> createMomentTensorIso(Double value) {
        return new JAXBElement<Double>(_MomentTensorIso_QNAME, Double.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "greensFunctionID", scope = MomentTensor.class)
    public JAXBElement<String> createMomentTensorGreensFunctionID(String value) {
        return new JAXBElement<String>(_MomentTensorGreensFunctionID_QNAME, String.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "filterID", scope = MomentTensor.class)
    public JAXBElement<String> createMomentTensorFilterID(String value) {
        return new JAXBElement<String>(_PickFilterID_QNAME, String.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SourceTimeFunction }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SourceTimeFunction }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "sourceTimeFunction", scope = MomentTensor.class)
    public JAXBElement<SourceTimeFunction> createMomentTensorSourceTimeFunction(SourceTimeFunction value) {
        return new JAXBElement<SourceTimeFunction>(_MomentTensorSourceTimeFunction_QNAME, SourceTimeFunction.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "methodID", scope = MomentTensor.class)
    public JAXBElement<String> createMomentTensorMethodID(String value) {
        return new JAXBElement<String>(_PickMethodID_QNAME, String.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MomentTensorCategory }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MomentTensorCategory }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "category", scope = MomentTensor.class)
    public JAXBElement<MomentTensorCategory> createMomentTensorCategory(MomentTensorCategory value) {
        return new JAXBElement<MomentTensorCategory>(_AmplitudeCategory_QNAME, MomentTensorCategory.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MTInversionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MTInversionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "inversionType", scope = MomentTensor.class)
    public JAXBElement<MTInversionType> createMomentTensorInversionType(MTInversionType value) {
        return new JAXBElement<MTInversionType>(_MomentTensorInversionType_QNAME, MTInversionType.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationInfo", scope = MomentTensor.class)
    public JAXBElement<CreationInfo> createMomentTensorCreationInfo(CreationInfo value) {
        return new JAXBElement<CreationInfo>(_EventCreationInfo_QNAME, CreationInfo.class, MomentTensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "semiMajorAxisLength", scope = ConfidenceEllipsoid.class)
    public JAXBElement<Double> createConfidenceEllipsoidSemiMajorAxisLength(Double value) {
        return new JAXBElement<Double>(_ConfidenceEllipsoidSemiMajorAxisLength_QNAME, Double.class, ConfidenceEllipsoid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "semiMinorAxisLength", scope = ConfidenceEllipsoid.class)
    public JAXBElement<Double> createConfidenceEllipsoidSemiMinorAxisLength(Double value) {
        return new JAXBElement<Double>(_ConfidenceEllipsoidSemiMinorAxisLength_QNAME, Double.class, ConfidenceEllipsoid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "semiIntermediateAxisLength", scope = ConfidenceEllipsoid.class)
    public JAXBElement<Double> createConfidenceEllipsoidSemiIntermediateAxisLength(Double value) {
        return new JAXBElement<Double>(_ConfidenceEllipsoidSemiIntermediateAxisLength_QNAME, Double.class, ConfidenceEllipsoid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "majorAxisPlunge", scope = ConfidenceEllipsoid.class)
    public JAXBElement<Double> createConfidenceEllipsoidMajorAxisPlunge(Double value) {
        return new JAXBElement<Double>(_ConfidenceEllipsoidMajorAxisPlunge_QNAME, Double.class, ConfidenceEllipsoid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "majorAxisAzimuth", scope = ConfidenceEllipsoid.class)
    public JAXBElement<Double> createConfidenceEllipsoidMajorAxisAzimuth(Double value) {
        return new JAXBElement<Double>(_ConfidenceEllipsoidMajorAxisAzimuth_QNAME, Double.class, ConfidenceEllipsoid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "majorAxisRotation", scope = ConfidenceEllipsoid.class)
    public JAXBElement<Double> createConfidenceEllipsoidMajorAxisRotation(Double value) {
        return new JAXBElement<Double>(_ConfidenceEllipsoidMajorAxisRotation_QNAME, Double.class, ConfidenceEllipsoid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodalPlane }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NodalPlane }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "nodalPlane1", scope = NodalPlanes.class)
    public JAXBElement<NodalPlane> createNodalPlanesNodalPlane1(NodalPlane value) {
        return new JAXBElement<NodalPlane>(_NodalPlanesNodalPlane1_QNAME, NodalPlane.class, NodalPlanes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodalPlane }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NodalPlane }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "nodalPlane2", scope = NodalPlanes.class)
    public JAXBElement<NodalPlane> createNodalPlanesNodalPlane2(NodalPlane value) {
        return new JAXBElement<NodalPlane>(_NodalPlanesNodalPlane2_QNAME, NodalPlane.class, NodalPlanes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SourceTimeFunctionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SourceTimeFunctionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "type", scope = SourceTimeFunction.class)
    public JAXBElement<SourceTimeFunctionType> createSourceTimeFunctionType(SourceTimeFunctionType value) {
        return new JAXBElement<SourceTimeFunctionType>(_EventType_QNAME, SourceTimeFunctionType.class, SourceTimeFunction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "duration", scope = SourceTimeFunction.class)
    public JAXBElement<Double> createSourceTimeFunctionDuration(Double value) {
        return new JAXBElement<Double>(_SourceTimeFunctionDuration_QNAME, Double.class, SourceTimeFunction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "riseTime", scope = SourceTimeFunction.class)
    public JAXBElement<Double> createSourceTimeFunctionRiseTime(Double value) {
        return new JAXBElement<Double>(_SourceTimeFunctionRiseTime_QNAME, Double.class, SourceTimeFunction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "decayTime", scope = SourceTimeFunction.class)
    public JAXBElement<Double> createSourceTimeFunctionDecayTime(Double value) {
        return new JAXBElement<Double>(_SourceTimeFunctionDecayTime_QNAME, Double.class, SourceTimeFunction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "value", scope = IntegerQuantity.class)
    public JAXBElement<BigInteger> createIntegerQuantityValue(BigInteger value) {
        return new JAXBElement<BigInteger>(_IntegerQuantityValue_QNAME, BigInteger.class, IntegerQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "uncertainty", scope = IntegerQuantity.class)
    public JAXBElement<BigInteger> createIntegerQuantityUncertainty(BigInteger value) {
        return new JAXBElement<BigInteger>(_IntegerQuantityUncertainty_QNAME, BigInteger.class, IntegerQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "lowerUncertainty", scope = IntegerQuantity.class)
    public JAXBElement<BigInteger> createIntegerQuantityLowerUncertainty(BigInteger value) {
        return new JAXBElement<BigInteger>(_IntegerQuantityLowerUncertainty_QNAME, BigInteger.class, IntegerQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "upperUncertainty", scope = IntegerQuantity.class)
    public JAXBElement<BigInteger> createIntegerQuantityUpperUncertainty(BigInteger value) {
        return new JAXBElement<BigInteger>(_IntegerQuantityUpperUncertainty_QNAME, BigInteger.class, IntegerQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "confidenceLevel", scope = IntegerQuantity.class)
    public JAXBElement<Double> createIntegerQuantityConfidenceLevel(Double value) {
        return new JAXBElement<Double>(_OriginUncertaintyConfidenceLevel_QNAME, Double.class, IntegerQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "begin", scope = TimeWindow.class)
    public JAXBElement<Double> createTimeWindowBegin(Double value) {
        return new JAXBElement<Double>(_TimeWindowBegin_QNAME, Double.class, TimeWindow.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "end", scope = TimeWindow.class)
    public JAXBElement<Double> createTimeWindowEnd(Double value) {
        return new JAXBElement<Double>(_TimeWindowEnd_QNAME, Double.class, TimeWindow.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "reference", scope = TimeWindow.class)
    public JAXBElement<XMLGregorianCalendar> createTimeWindowReference(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TimeWindowReference_QNAME, XMLGregorianCalendar.class, TimeWindow.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "strike", scope = NodalPlane.class)
    public JAXBElement<RealQuantity> createNodalPlaneStrike(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_NodalPlaneStrike_QNAME, RealQuantity.class, NodalPlane.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "dip", scope = NodalPlane.class)
    public JAXBElement<RealQuantity> createNodalPlaneDip(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_NodalPlaneDip_QNAME, RealQuantity.class, NodalPlane.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "rake", scope = NodalPlane.class)
    public JAXBElement<RealQuantity> createNodalPlaneRake(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_NodalPlaneRake_QNAME, RealQuantity.class, NodalPlane.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "value", scope = RealQuantity.class)
    public JAXBElement<Double> createRealQuantityValue(Double value) {
        return new JAXBElement<Double>(_IntegerQuantityValue_QNAME, Double.class, RealQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "uncertainty", scope = RealQuantity.class)
    public JAXBElement<Double> createRealQuantityUncertainty(Double value) {
        return new JAXBElement<Double>(_IntegerQuantityUncertainty_QNAME, Double.class, RealQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "lowerUncertainty", scope = RealQuantity.class)
    public JAXBElement<Double> createRealQuantityLowerUncertainty(Double value) {
        return new JAXBElement<Double>(_IntegerQuantityLowerUncertainty_QNAME, Double.class, RealQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "upperUncertainty", scope = RealQuantity.class)
    public JAXBElement<Double> createRealQuantityUpperUncertainty(Double value) {
        return new JAXBElement<Double>(_IntegerQuantityUpperUncertainty_QNAME, Double.class, RealQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "confidenceLevel", scope = RealQuantity.class)
    public JAXBElement<Double> createRealQuantityConfidenceLevel(Double value) {
        return new JAXBElement<Double>(_OriginUncertaintyConfidenceLevel_QNAME, Double.class, RealQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "associatedPhaseCount", scope = OriginQuality.class)
    public JAXBElement<BigInteger> createOriginQualityAssociatedPhaseCount(BigInteger value) {
        return new JAXBElement<BigInteger>(_OriginQualityAssociatedPhaseCount_QNAME, BigInteger.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "usedPhaseCount", scope = OriginQuality.class)
    public JAXBElement<BigInteger> createOriginQualityUsedPhaseCount(BigInteger value) {
        return new JAXBElement<BigInteger>(_OriginQualityUsedPhaseCount_QNAME, BigInteger.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "associatedStationCount", scope = OriginQuality.class)
    public JAXBElement<BigInteger> createOriginQualityAssociatedStationCount(BigInteger value) {
        return new JAXBElement<BigInteger>(_OriginQualityAssociatedStationCount_QNAME, BigInteger.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "usedStationCount", scope = OriginQuality.class)
    public JAXBElement<BigInteger> createOriginQualityUsedStationCount(BigInteger value) {
        return new JAXBElement<BigInteger>(_OriginQualityUsedStationCount_QNAME, BigInteger.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "depthPhaseCount", scope = OriginQuality.class)
    public JAXBElement<BigInteger> createOriginQualityDepthPhaseCount(BigInteger value) {
        return new JAXBElement<BigInteger>(_OriginQualityDepthPhaseCount_QNAME, BigInteger.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "standardError", scope = OriginQuality.class)
    public JAXBElement<Double> createOriginQualityStandardError(Double value) {
        return new JAXBElement<Double>(_OriginQualityStandardError_QNAME, Double.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "azimuthalGap", scope = OriginQuality.class)
    public JAXBElement<Double> createOriginQualityAzimuthalGap(Double value) {
        return new JAXBElement<Double>(_MagnitudeAzimuthalGap_QNAME, Double.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "secondaryAzimuthalGap", scope = OriginQuality.class)
    public JAXBElement<Double> createOriginQualitySecondaryAzimuthalGap(Double value) {
        return new JAXBElement<Double>(_OriginQualitySecondaryAzimuthalGap_QNAME, Double.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "groundTruthLevel", scope = OriginQuality.class)
    public JAXBElement<String> createOriginQualityGroundTruthLevel(String value) {
        return new JAXBElement<String>(_OriginQualityGroundTruthLevel_QNAME, String.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "maximumDistance", scope = OriginQuality.class)
    public JAXBElement<Double> createOriginQualityMaximumDistance(Double value) {
        return new JAXBElement<Double>(_OriginQualityMaximumDistance_QNAME, Double.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "minimumDistance", scope = OriginQuality.class)
    public JAXBElement<Double> createOriginQualityMinimumDistance(Double value) {
        return new JAXBElement<Double>(_OriginQualityMinimumDistance_QNAME, Double.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "medianDistance", scope = OriginQuality.class)
    public JAXBElement<Double> createOriginQualityMedianDistance(Double value) {
        return new JAXBElement<Double>(_OriginQualityMedianDistance_QNAME, Double.class, OriginQuality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "Mrr", scope = Tensor.class)
    public JAXBElement<RealQuantity> createTensorMrr(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_TensorMrr_QNAME, RealQuantity.class, Tensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "Mtt", scope = Tensor.class)
    public JAXBElement<RealQuantity> createTensorMtt(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_TensorMtt_QNAME, RealQuantity.class, Tensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "Mpp", scope = Tensor.class)
    public JAXBElement<RealQuantity> createTensorMpp(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_TensorMpp_QNAME, RealQuantity.class, Tensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "Mrt", scope = Tensor.class)
    public JAXBElement<RealQuantity> createTensorMrt(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_TensorMrt_QNAME, RealQuantity.class, Tensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "Mrp", scope = Tensor.class)
    public JAXBElement<RealQuantity> createTensorMrp(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_TensorMrp_QNAME, RealQuantity.class, Tensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "Mtp", scope = Tensor.class)
    public JAXBElement<RealQuantity> createTensorMtp(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_TensorMtp_QNAME, RealQuantity.class, Tensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "year", scope = CompositeTime.class)
    public JAXBElement<IntegerQuantity> createCompositeTimeYear(IntegerQuantity value) {
        return new JAXBElement<IntegerQuantity>(_CompositeTimeYear_QNAME, IntegerQuantity.class, CompositeTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "month", scope = CompositeTime.class)
    public JAXBElement<IntegerQuantity> createCompositeTimeMonth(IntegerQuantity value) {
        return new JAXBElement<IntegerQuantity>(_CompositeTimeMonth_QNAME, IntegerQuantity.class, CompositeTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "day", scope = CompositeTime.class)
    public JAXBElement<IntegerQuantity> createCompositeTimeDay(IntegerQuantity value) {
        return new JAXBElement<IntegerQuantity>(_CompositeTimeDay_QNAME, IntegerQuantity.class, CompositeTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "hour", scope = CompositeTime.class)
    public JAXBElement<IntegerQuantity> createCompositeTimeHour(IntegerQuantity value) {
        return new JAXBElement<IntegerQuantity>(_CompositeTimeHour_QNAME, IntegerQuantity.class, CompositeTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IntegerQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "minute", scope = CompositeTime.class)
    public JAXBElement<IntegerQuantity> createCompositeTimeMinute(IntegerQuantity value) {
        return new JAXBElement<IntegerQuantity>(_CompositeTimeMinute_QNAME, IntegerQuantity.class, CompositeTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "second", scope = CompositeTime.class)
    public JAXBElement<RealQuantity> createCompositeTimeSecond(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_CompositeTimeSecond_QNAME, RealQuantity.class, CompositeTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataUsedWaveType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataUsedWaveType }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "waveType", scope = DataUsed.class)
    public JAXBElement<DataUsedWaveType> createDataUsedWaveType(DataUsedWaveType value) {
        return new JAXBElement<DataUsedWaveType>(_DataUsedWaveType_QNAME, DataUsedWaveType.class, DataUsed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "stationCount", scope = DataUsed.class)
    public JAXBElement<BigInteger> createDataUsedStationCount(BigInteger value) {
        return new JAXBElement<BigInteger>(_MagnitudeStationCount_QNAME, BigInteger.class, DataUsed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "componentCount", scope = DataUsed.class)
    public JAXBElement<BigInteger> createDataUsedComponentCount(BigInteger value) {
        return new JAXBElement<BigInteger>(_DataUsedComponentCount_QNAME, BigInteger.class, DataUsed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "shortestPeriod", scope = DataUsed.class)
    public JAXBElement<Double> createDataUsedShortestPeriod(Double value) {
        return new JAXBElement<Double>(_DataUsedShortestPeriod_QNAME, Double.class, DataUsed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "longestPeriod", scope = DataUsed.class)
    public JAXBElement<Double> createDataUsedLongestPeriod(Double value) {
        return new JAXBElement<Double>(_DataUsedLongestPeriod_QNAME, Double.class, DataUsed.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Axis }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Axis }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "tAxis", scope = PrincipalAxes.class)
    public JAXBElement<Axis> createPrincipalAxesTAxis(Axis value) {
        return new JAXBElement<Axis>(_PrincipalAxesTAxis_QNAME, Axis.class, PrincipalAxes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Axis }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Axis }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "pAxis", scope = PrincipalAxes.class)
    public JAXBElement<Axis> createPrincipalAxesPAxis(Axis value) {
        return new JAXBElement<Axis>(_PrincipalAxesPAxis_QNAME, Axis.class, PrincipalAxes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Axis }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Axis }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "nAxis", scope = PrincipalAxes.class)
    public JAXBElement<Axis> createPrincipalAxesNAxis(Axis value) {
        return new JAXBElement<Axis>(_PrincipalAxesNAxis_QNAME, Axis.class, PrincipalAxes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "azimuth", scope = Axis.class)
    public JAXBElement<RealQuantity> createAxisAzimuth(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_ArrivalAzimuth_QNAME, RealQuantity.class, Axis.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "plunge", scope = Axis.class)
    public JAXBElement<RealQuantity> createAxisPlunge(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_AxisPlunge_QNAME, RealQuantity.class, Axis.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "length", scope = Axis.class)
    public JAXBElement<RealQuantity> createAxisLength(RealQuantity value) {
        return new JAXBElement<RealQuantity>(_AxisLength_QNAME, RealQuantity.class, Axis.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "agencyID", scope = CreationInfo.class)
    public JAXBElement<String> createCreationInfoAgencyID(String value) {
        return new JAXBElement<String>(_CreationInfoAgencyID_QNAME, String.class, CreationInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "agencyURI", scope = CreationInfo.class)
    public JAXBElement<String> createCreationInfoAgencyURI(String value) {
        return new JAXBElement<String>(_CreationInfoAgencyURI_QNAME, String.class, CreationInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "author", scope = CreationInfo.class)
    public JAXBElement<String> createCreationInfoAuthor(String value) {
        return new JAXBElement<String>(_CreationInfoAuthor_QNAME, String.class, CreationInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "authorURI", scope = CreationInfo.class)
    public JAXBElement<String> createCreationInfoAuthorURI(String value) {
        return new JAXBElement<String>(_CreationInfoAuthorURI_QNAME, String.class, CreationInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "creationTime", scope = CreationInfo.class)
    public JAXBElement<XMLGregorianCalendar> createCreationInfoCreationTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CreationInfoCreationTime_QNAME, XMLGregorianCalendar.class, CreationInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "version", scope = CreationInfo.class)
    public JAXBElement<String> createCreationInfoVersion(String value) {
        return new JAXBElement<String>(_CreationInfoVersion_QNAME, String.class, CreationInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "value", scope = TimeQuantity.class)
    public JAXBElement<XMLGregorianCalendar> createTimeQuantityValue(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_IntegerQuantityValue_QNAME, XMLGregorianCalendar.class, TimeQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "uncertainty", scope = TimeQuantity.class)
    public JAXBElement<Double> createTimeQuantityUncertainty(Double value) {
        return new JAXBElement<Double>(_IntegerQuantityUncertainty_QNAME, Double.class, TimeQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "lowerUncertainty", scope = TimeQuantity.class)
    public JAXBElement<Double> createTimeQuantityLowerUncertainty(Double value) {
        return new JAXBElement<Double>(_IntegerQuantityLowerUncertainty_QNAME, Double.class, TimeQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "upperUncertainty", scope = TimeQuantity.class)
    public JAXBElement<Double> createTimeQuantityUpperUncertainty(Double value) {
        return new JAXBElement<Double>(_IntegerQuantityUpperUncertainty_QNAME, Double.class, TimeQuantity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/bed/1.2", name = "confidenceLevel", scope = TimeQuantity.class)
    public JAXBElement<Double> createTimeQuantityConfidenceLevel(Double value) {
        return new JAXBElement<Double>(_OriginUncertaintyConfidenceLevel_QNAME, Double.class, TimeQuantity.class, value);
    }

}
