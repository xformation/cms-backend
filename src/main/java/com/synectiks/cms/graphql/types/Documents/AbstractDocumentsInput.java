package com.synectiks.cms.graphql.types.Documents;

import java.util.Objects;

public class AbstractDocumentsInput {
    private Long id;
    private String documentName;
    private String documentFilePath;
    private String isFlatFileStorage;
    private String isMsOneDriveStorage;
    private String oneDrivePath;
    private String isOakStorage;
    private String oakPath;
    private String isAwsStorage;
    private String awsPath;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    @Override
    public String toString() {
        return "AbstractDocumentsInput{" +
            "id=" + id +
            ", documentName='" + documentName + '\'' +
            ", documentFilePath='" + documentFilePath + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDocumentsInput)) return false;
        AbstractDocumentsInput that = (AbstractDocumentsInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getDocumentName(), that.getDocumentName()) &&
            Objects.equals(getDocumentFilePath(), that.getDocumentFilePath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDocumentName(), getDocumentFilePath());
    }

	public String getIsFlatFileStorage() {
		return isFlatFileStorage;
	}

	public void setIsFlatFileStorage(String isFlatFileStorage) {
		this.isFlatFileStorage = isFlatFileStorage;
	}

	public String getIsMsOneDriveStorage() {
		return isMsOneDriveStorage;
	}

	public void setIsMsOneDriveStorage(String isMsOneDriveStorage) {
		this.isMsOneDriveStorage = isMsOneDriveStorage;
	}

	public String getOneDrivePath() {
		return oneDrivePath;
	}

	public void setOneDrivePath(String oneDrivePath) {
		this.oneDrivePath = oneDrivePath;
	}

	public String getIsOakStorage() {
		return isOakStorage;
	}

	public void setIsOakStorage(String isOakStorage) {
		this.isOakStorage = isOakStorage;
	}

	public String getOakPath() {
		return oakPath;
	}

	public void setOakPath(String oakPath) {
		this.oakPath = oakPath;
	}

	public String getIsAwsStorage() {
		return isAwsStorage;
	}

	public void setIsAwsStorage(String isAwsStorage) {
		this.isAwsStorage = isAwsStorage;
	}

	public String getAwsPath() {
		return awsPath;
	}

	public void setAwsPath(String awsPath) {
		this.awsPath = awsPath;
	}
}
