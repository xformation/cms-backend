package com.synectiks.cms.graphql.types.College;

public class AbstractCollegeInput {
    private Long id;
    private String shortName;
    private String logoPath;
    private String backgroundImagePath;
    private String instructionInformation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getInstructionInformation() {
        return instructionInformation;
    }

    public void setInstructionInformation(String instructionInformation) {
        this.instructionInformation = instructionInformation;
    }

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getBackgroundImagePath() {
		return backgroundImagePath;
	}

	public void setBackgroundImagePath(String backgroundImagePath) {
		this.backgroundImagePath = backgroundImagePath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backgroundImagePath == null) ? 0 : backgroundImagePath.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instructionInformation == null) ? 0 : instructionInformation.hashCode());
		result = prime * result + ((logoPath == null) ? 0 : logoPath.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCollegeInput other = (AbstractCollegeInput) obj;
		if (backgroundImagePath == null) {
			if (other.backgroundImagePath != null)
				return false;
		} else if (!backgroundImagePath.equals(other.backgroundImagePath))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instructionInformation == null) {
			if (other.instructionInformation != null)
				return false;
		} else if (!instructionInformation.equals(other.instructionInformation))
			return false;
		if (logoPath == null) {
			if (other.logoPath != null)
				return false;
		} else if (!logoPath.equals(other.logoPath))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractCollegeInput [id=" + id + ", shortName=" + shortName + ", logoPath=" + logoPath
				+ ", backgroundImagePath=" + backgroundImagePath + ", instructionInformation=" + instructionInformation
				+ "]";
	}

    
}
