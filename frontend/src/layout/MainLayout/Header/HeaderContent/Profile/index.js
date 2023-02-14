import PropTypes from 'prop-types';
import {useEffect, useRef, useState} from 'react';

// material-ui
import {useTheme} from '@mui/material/styles';
import {Box, ButtonBase, Paper, Popper, Stack, Typography} from '@mui/material';

// project import
import Transitions from 'components/@extended/Transitions';

// assets

// tab panel wrapper
function TabPanel({children, value, index, ...other}) {
  return (
      <div role="tabpanel" hidden={value !== index}
           id={`profile-tabpanel-${index}`}
           aria-labelledby={`profile-tab-${index}`} {...other}>
        {value === index && children}
      </div>
  );
}

TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.any.isRequired,
    value: PropTypes.any.isRequired
};


// ==============================|| HEADER CONTENT - PROFILE ||============================== //

const Profile = () => {
    const theme = useTheme();

    const handleLogout = async () => {
        // logout
    };

    const anchorRef = useRef(null);
    const [open, setOpen] = useState(false);
    const handleToggle = () => {
        setOpen((prevOpen) => !prevOpen);
    };

    const handleClose = (event) => {
        if (anchorRef.current && anchorRef.current.contains(event.target)) {
            return;
        }
        setOpen(false);
    };

    const [value, setValue] = useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const iconBackColorOpen = 'grey.300';

    return (
        <Box sx={{ flexShrink: 0, ml: 0.75 }}>
            <ButtonBase
                sx={{
                    p: 0.25,
                  bgcolor: open ? iconBackColorOpen : 'transparent',
                  borderRadius: 1,
                  '&:hover': {bgcolor: 'secondary.lighter'}
                }}
                aria-label="open profile"
                ref={anchorRef}
                aria-controls={open ? 'profile-grow' : undefined}
                aria-haspopup="true"
                onClick={handleToggle}
            >
              <Stack direction="row" spacing={2} alignItems="center"
                     sx={{p: 0.5}}>
                {/*<Avatar alt="profile user" src={avatar1} sx={{ width: 32, height: 32 }} />*/}
                <Typography variant="subtitle1">Teddy</Typography>
              </Stack>
            </ButtonBase>
          <Popper
              placement="bottom-end"
              open={open}
              anchorEl={anchorRef.current}
              role={undefined}
              transition
              disablePortal
              popperOptions={{
                    modifiers: [
                        {
                            name: 'offset',
                            options: {
                                offset: [0, 9]
                            }
                        }
                    ]
                }}
            >
                {({ TransitionProps }) => (
                    <Transitions type="fade" in={open} {...TransitionProps}>
                        {open && (
                            <Paper
                                sx={{
                                  boxShadow: theme.customShadows.z1,
                                  width: 290,
                                  minWidth: 240,
                                  maxWidth: 290,
                                  [theme.breakpoints.down('md')]: {
                                    maxWidth: 250
                                  }
                                }}
                            >
                            </Paper>
                        )}
                    </Transitions>
                )}
            </Popper>
        </Box>
    );
};

export default Profile;
